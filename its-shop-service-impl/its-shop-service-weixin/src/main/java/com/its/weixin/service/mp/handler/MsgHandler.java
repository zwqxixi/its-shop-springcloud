package com.its.weixin.service.mp.handler;
import com.its.common.core.utils.RandomNumberUtils;
import com.its.common.core.utils.RegexUtils;
import com.its.weixin.service.mp.builder.TextBuilder;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static me.chanjar.weixin.common.api.WxConsts.XmlMsgType;

/**
 * @author Binary Wang(https://github.com/binarywang)
 */
@Component
@Slf4j
public class MsgHandler extends AbstractHandler {


    @Value("${its.weixin.registration.code.message}")
    private String codeMessage;

    @Value("${its.weixin.default.registration.code.message}")
    private String defaultCodeMessage;

    private static final String REGIST_CODE="regist_code:";

    private static final int EXPRIED_MINUTES = 30;

    @Autowired
    private StringRedisTemplate redisTemplate;


    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage,
                                    Map<String, Object> context, WxMpService weixinService,
                                    WxSessionManager sessionManager) {

        if (!wxMessage.getMsgType().equals(XmlMsgType.EVENT)) {
            //TODO 可以选择将消息保存到本地
        }

        //当用户输入关键词如“你好”，“客服”等，并且有客服在线时，把消息转发给在线客服
        try {
            if (StringUtils.startsWithAny(wxMessage.getContent(), "你好", "客服")
                && weixinService.getKefuService().kfOnlineList()
                .getKfOnlineList().size() > 0) {
                return WxMpXmlOutMessage.TRANSFER_CUSTOMER_SERVICE()
                    .fromUser(wxMessage.getToUser())
                    .toUser(wxMessage.getFromUser()).build();
            }
        } catch (WxErrorException e) {
            e.printStackTrace();
        }
        String fromContent=wxMessage.getContent();
        if(StringUtils.isNotEmpty(fromContent)&& RegexUtils.checkPhone(fromContent)){
            log.info("订阅号入参:手机号:{}",fromContent);
            String code= RandomNumberUtils.getRandomNumber6();//生成注册码
            redisTemplate.opsForValue().set(REGIST_CODE+fromContent,code,EXPRIED_MINUTES, TimeUnit.MINUTES);
            //TODO 组装回复消息
            String content =codeMessage.replaceAll("registrationCodeMessage",code+"");
            return new TextBuilder().build(content, wxMessage, weixinService);
        }
        return new TextBuilder().build(defaultCodeMessage, wxMessage, weixinService);

    }

}

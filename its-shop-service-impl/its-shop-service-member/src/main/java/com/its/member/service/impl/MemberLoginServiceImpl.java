package com.its.member.service.impl;

import com.its.common.core.constant.ApiResponseVo;
import com.its.common.core.constant.ClientEnum;
import com.its.common.core.constant.ErrorCodeEnum;
import com.its.common.core.constant.RedisContans;
import com.its.common.core.token.GenerateToken;
import com.its.common.core.transaction.RedisDataSourceTransaction;
import com.its.common.core.utils.ClassConversionUtils;
import com.its.common.core.utils.RedisUtils;
import com.its.member.api.dto.ShopUserDto;
import com.its.member.api.dto.UserTokenDto;
import com.its.member.api.service.MemberLoginService;
import com.its.member.api.vo.ShopUserVo;
import com.its.member.domain.entity.ShopUser;
import com.its.member.domain.entity.ShopUserToken;
import com.its.member.domain.mapper.ShopUserMapper;
import com.its.member.domain.mapper.ShopUserTokenMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.transaction.TransactionStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;

/**
 * @Project: its-shop-parent
 * @Auther: wenqin.zhao
 * @CreateDate: 2019/11/21 15:27
 * @Description: 用户登录接口实现类
 */
@RestController
public class MemberLoginServiceImpl implements MemberLoginService{


     @Resource
     private ShopUserMapper shopUserMapper;

     @Resource
     private ShopUserTokenMapper shopUserTokenMapper;

     @Resource
     private GenerateToken generateToken;

     @Resource
     private RedisUtils redisUtils;

     @Resource
     private RedisDataSourceTransaction redisDataSourceTransaction;

     /**
      *
      * @Description: 用户唯一登录实现
      * @param  userTokenDto(token,loginType)
      * @Return:
      * @Auther: wenqin.zhao
      * @CreateDate: 2019/11/21 16:06
      */
     @Override
     public ApiResponseVo onlyUserLogin(@RequestBody UserTokenDto userTokenDto) throws Exception{
          //TODO  1.验证入参是否正确 (登录类型是否符合)
          //      2.验证手机号和密码是否正确
          //      3.根据userId和loginType查询用户是否已经登录
          //      4.如果已经登录,remove Redis数据库中的token,更新唯一登录表数据状态为不可用
          //      5.生成新的token 存入Redis数据库,插入表中
          String mobile = userTokenDto.getMobile();
          String password = userTokenDto.getPassword();
          if(StringUtils.isEmpty(mobile) || StringUtils.isEmpty(password)){
               return ApiResponseVo.genError(1L);
          }
          Integer type = userTokenDto.getType();
          if(type == null && !ClientEnum.getAllClientType().contains(type)){
               return ApiResponseVo.genError(1L);
          }
          ShopUser shopUser = shopUserMapper.selectByMobileAndPassword(mobile,password);
          if(shopUser == null){
               return ApiResponseVo.genError(2L);
          }
          Integer userId = shopUser.getUserId();
          String loginType = ClientEnum.getClientTypeByCode(type);
          ShopUserToken userToken = shopUserTokenMapper.selectByUserIdAndLoginType(userId,loginType);
          //判断用户是否已经在相同设备登录
          TransactionStatus transactionStatus = redisDataSourceTransaction.begin();//开启事务
          if(userToken != null){
               //先删除缓存中的token   更新数据库表token的可用状态
               Boolean b = redisUtils.delKey(userToken.getToken());
               userToken.setIsAvailability(2);
               int i = shopUserTokenMapper.updateByPrimaryKeySelective(userToken);
               if(i < 0){
                    redisDataSourceTransaction.rollback(transactionStatus);
                    return ApiResponseVo.genError(ErrorCodeEnum.SYSTEM_ERROR);
               }
          }
          String newToken = generateToken.createToken(RedisContans.LOGIN_PREFIX+loginType,userId.toString());
          ShopUserToken shopUserToken = new ShopUserToken();
          BeanUtils.copyProperties(userTokenDto,shopUserToken);
          shopUserToken.setLoginType(loginType);
          shopUserToken.setToken(newToken);
          shopUserToken.setIsAvailability(1);
          shopUserToken.setUserId(userId);
          int i = shopUserTokenMapper.insertSelective(shopUserToken);
          if(i< 0){
               redisDataSourceTransaction.rollback(transactionStatus);
               return ApiResponseVo.genError(ErrorCodeEnum.SYSTEM_ERROR);
          }
          redisDataSourceTransaction.commit(transactionStatus);
          //问题？如何保证redis中数据状态与msql数据库中的值保持一致？
          //采用mysql事务机制(采用手动事务机制)
          return ApiResponseVo.genSuccess(newToken);
     }

     @Override
     public ApiResponseVo<ShopUserVo> authLogin(String mobile, String password) {
          if(StringUtils.isEmpty(mobile) || StringUtils.isEmpty(password)){
               return ApiResponseVo.genError(1L);
          }
          ShopUser shopUser = shopUserMapper.selectByMobileAndPassword(mobile,password);
          if(shopUser == null){
               return ApiResponseVo.genError(2L);
          }
          ShopUserVo shopUserVo = new ShopUserVo();
          BeanUtils.copyProperties(shopUser,shopUserVo);
          return ApiResponseVo.genSuccess(shopUserVo);
     }
}

package com.its.basics.auth.config.auth;

import com.its.common.core.utils.ObjectMapperUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.oauth2.common.exceptions.InvalidClientException;
import org.springframework.security.oauth2.provider.ClientAlreadyExistsException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.NoSuchClientException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import javax.sql.DataSource;
/**
  * @description: CustomClientDetailService检查第三方客户端信息的合法性
  * @param:
  * @return:
  * @author: wenqin.zhao
  * @createDate: 19:53 2020/5/11
  */
public class CustomClientDetailService extends JdbcClientDetailsService {

    private static final String OAUTH_CLIENT_DETAILS = "oauth_client_details:";
    private final StringRedisTemplate redisTemplate;

    public CustomClientDetailService(DataSource dataSource, StringRedisTemplate redisTemplate) {
        super(dataSource);
        this.redisTemplate = redisTemplate;
    }

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws InvalidClientException {
        // 首先从redis 中取加载
        String value = redisTemplate.opsForValue().get(OAUTH_CLIENT_DETAILS + clientId);
        ClientDetails clientDetails = null;
        if (StringUtils.isNotBlank(value)) {
            clientDetails = ObjectMapperUtils.toObject(value, BaseClientDetails.class);
        } else {
            // 缓存中找不到，从数据库中加载
            clientDetails = super.loadClientByClientId(clientId);
            // 并设置到缓存中
            redisTemplate.opsForValue().set(OAUTH_CLIENT_DETAILS + clientId, ObjectMapperUtils.toString(clientDetails));
        }
        return clientDetails;
    }

    @Override
    public void addClientDetails(ClientDetails clientDetails) throws ClientAlreadyExistsException {
        super.addClientDetails(clientDetails);
        // 并设置到缓存中
        redisTemplate.opsForValue().set(OAUTH_CLIENT_DETAILS + clientDetails.getClientId(), ObjectMapperUtils.toString(clientDetails));

    }

    @Override
    public void updateClientDetails(ClientDetails clientDetails) throws NoSuchClientException {
        super.updateClientDetails(clientDetails);
        // 并设置到缓存中
        redisTemplate.opsForValue().set(OAUTH_CLIENT_DETAILS + clientDetails.getClientId(), ObjectMapperUtils.toString(clientDetails));

    }

    @Override
    public void updateClientSecret(String clientId, String secret) throws NoSuchClientException {
        super.updateClientSecret(clientId, secret);
        // 数据库中加载
        ClientDetails clientDetails = super.loadClientByClientId(clientId);
        // 并设置到缓存中
        redisTemplate.opsForValue().set(OAUTH_CLIENT_DETAILS + clientId, ObjectMapperUtils.toString(clientDetails));

    }

    @Override
    public void removeClientDetails(String clientId) throws NoSuchClientException {
        super.removeClientDetails(clientId);
        // 从缓存中删除
        redisTemplate.delete(OAUTH_CLIENT_DETAILS + clientId);

    }
}

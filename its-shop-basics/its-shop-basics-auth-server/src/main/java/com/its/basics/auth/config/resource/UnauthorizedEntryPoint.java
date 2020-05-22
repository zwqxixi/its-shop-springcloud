package com.its.basics.auth.config.resource;

import com.its.common.core.constant.ApiResponseVo;
import com.its.common.core.constant.ErrorCodeEnum;
import com.its.common.core.utils.ObjectMapperUtils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author wenqin.zhao
 * @date 2018-02-28
 */
public class UnauthorizedEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.getWriter().write(ObjectMapperUtils.toString(ApiResponseVo.genError(ErrorCodeEnum.USER_NOT_LOGIN)));
    }

}

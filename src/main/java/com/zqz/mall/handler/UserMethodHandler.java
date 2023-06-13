package com.zqz.mall.handler;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.zqz.mall.annotation.UserToken;
import com.zqz.mall.dao.MallUserMapper;
import com.zqz.mall.dao.MallUserTokenMapper;
import com.zqz.mall.entity.MallUser;
import com.zqz.mall.entity.MallUserToken;
import com.zqz.mall.enums.ResultEnum;
import com.zqz.mall.exception.MallException;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.annotation.Resource;

/**
 * @Author: ZQZ
 * @Description: 登录拦截
 * @ClassName: UserMethodHandler
 * @Date: Created in 17:37 2023-6-12
 */
@Component
public class UserMethodHandler implements HandlerMethodArgumentResolver {

    private static final String TOKEN = "token";
    private static final Integer TOKEN_LENGTH = 32;
    @Resource
    private MallUserTokenMapper userTokenMapper;
    @Resource
    private MallUserMapper userMapper;

    public UserMethodHandler() {

    }

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        if (methodParameter.hasParameterAnnotation(UserToken.class)) {
            return true;
        }
        return false;
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        if ((methodParameter.getParameterAnnotation(UserToken.class)) instanceof UserToken) {
            String token = nativeWebRequest.getHeader(TOKEN);
            if (StrUtil.isNotBlank(token) && token.length() == TOKEN_LENGTH) {
                MallUserToken userToken = userTokenMapper.selectByToken(token);
                if (ObjectUtil.isNotEmpty(userToken) && userToken.getExpireTime().getTime() <= System.currentTimeMillis()) {
                    //过期
                    MallException.fail(ResultEnum.TOKEN_EXPIRE_ERROR.getResult());
                }
                MallUser user = userMapper.selectByPrimaryKey(userToken.getId());
                if (ObjectUtil.isEmpty(user)) {
                    //用户不存在
                    MallException.fail(ResultEnum.USER_NULL_ERROR.getResult());
                }
                if (user.getLockedFlag().intValue() == 1) {
                    //是否锁定
                    MallException.fail(ResultEnum.LOGIN_USER_LOCKED_ERROR.getResult());
                }
                return user;
            } else {
                MallException.fail(ResultEnum.NOT_LOGIN_ERROR.getResult());
            }
        }
        return null;
    }
}

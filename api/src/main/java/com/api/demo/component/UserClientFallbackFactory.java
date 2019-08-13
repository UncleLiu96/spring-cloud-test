package com.api.demo.component;

import com.api.demo.feign.ApiFeign;
import com.common.pojo.User;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class UserClientFallbackFactory implements FallbackFactory<ApiFeign> {
    @Override
    public ApiFeign create(Throwable throwable) {
        return new ApiFeign() {
            @Override
            public User select(Integer uId) {
                System.out.println("*************"+uId);
                User user = new User();
                user.setuName("哈哈哈哈哈");
                return user;
            }
        };
    }
}

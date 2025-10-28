package com.kaismemo.trade.service;

import com.baomidou.mybatisplus.test.autoconfigure.MybatisPlusTest;
import com.kaismemo.trade.config.ConverterTestConfig;
import com.kaismemo.trade.config.MyBatisPlusTestConfig;
import com.kaismemo.trade.domain.bo.CustomerBo;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Import;
import org.springframework.dao.DuplicateKeyException;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * CustomerService 的轻量级集成测试类
 *
 * @author reallth
 * @since 1.0.0
 */
@MybatisPlusTest
@Import({MyBatisPlusTestConfig.class, ConverterTestConfig.class ,CustomerService.class})
@DisplayName("用户服务接口集成测试")
class CustomerServiceTest {
    @Resource
    private CustomerService customerService;

    @Test
    @DisplayName("合法用户注册测试")
    public void should_void_when_valid() {
        CustomerBo validBo = new CustomerBo() {{
            setName("Oguri Cap");
            setEmail("oguri_cap@kasamutsu.com");
            setGender("Female");
            setCountry("Japan");
        }};
        customerService.signup(validBo);
        // 此处应当查库确认记录存在，后续查询接口实现后连带测试
    }

    @Test
    @DisplayName("重复用户注册测试")
    public void should_error_when_duplicate() {
        CustomerBo validBo = new CustomerBo() {{
            setName("Oguri Cap");
            setEmail("oguri_cap@kasamutsu.com");
            setGender("Female");
            setCountry("Japan");
        }};
        customerService.signup(validBo);
        assertThrows(DuplicateKeyException.class, () -> customerService.signup(validBo));
    }
}

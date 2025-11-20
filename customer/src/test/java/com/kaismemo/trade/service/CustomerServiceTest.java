package com.kaismemo.trade.service;

import com.baomidou.mybatisplus.test.autoconfigure.MybatisPlusTest;
import com.kaismemo.trade.config.ConverterTestConfig;
import com.kaismemo.trade.config.MyBatisPlusTestConfig;
import com.kaismemo.trade.domain.bo.CustomerBo;
import com.kaismemo.trade.domain.req.CustomerQueryReq;
import com.kaismemo.trade.domain.vo.CustomerVo;
import com.kaismemo.trade.exception.BusinessException;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Import;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

/**
 * CustomerService 的轻量级集成测试类
 *
 * @author reallth
 * @since 1.0.0
 */
@MybatisPlusTest
@Import({MyBatisPlusTestConfig.class, ConverterTestConfig.class, CustomerService.class})
@DisplayName("用户服务接口集成测试")
class CustomerServiceTest {
    @Resource
    private CustomerService customerService;

    @Test
    @DisplayName("合法用户注册查询")
    public void should_void_when_valid() {
        CustomerBo validBo = new CustomerBo() {{
            setName("Oguri Cap");
            setEmail("oguri_cap@kasamutsu.com");
            setGender("Female");
            setCountry("Japan");
        }};
        CustomerQueryReq queryReq = new CustomerQueryReq() {{
            setName("Oguri Cap");
            setEmail("oguri_cap@kasamutsu.com");
            setGender("Female");
            setCountry("Japan");
            setPage(1);
            setPageSize(10);
        }};
        CustomerVo expected = new CustomerVo() {{
            setName("Oguri Cap");
            setEmail("oguri_cap@kasamutsu.com");
            setGender("Female");
            setCountry("Japan");
            setSignupDate(LocalDate.now());
        }};
        customerService.signup(validBo);
        CustomerVo actual = customerService.query(queryReq).getData().getFirst();
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("重复用户注册")
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

    @Test
    @DisplayName("合法用户更新查询")
    @Sql(scripts = "/sql/dummy_customers.sql")
    public void should_void_when_update_valid() {
        CustomerBo validBo = new CustomerBo() {{
            setEmail("oguri_cap@kasamutsu.com");
            setName("Oguri Chain");
        }};
        CustomerVo expected = new CustomerVo() {{
            setName("Oguri Chain");
            setEmail("oguri_cap@kasamutsu.com");
            setGender("Female");
            setCountry("Japan");
            setSignupDate(LocalDate.parse("2025-02-15"));
        }};
        CustomerQueryReq queryReq = new CustomerQueryReq() {{
            setEmail("oguri_cap@kasamutsu.com");
            setPage(1);
            setPageSize(10);
        }};
        customerService.updateByEmail(validBo);
        CustomerVo actual = customerService.query(queryReq).getData().getFirst();
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("非法用户更新查询")
    public void should_error_when_update_invalid() {
        CustomerBo invalidBo = new CustomerBo() {{
            setEmail("goda_katsuhito@joho.com");
            setName("Goda Complex");
        }};
        assertThrows(BusinessException.class, () -> customerService.updateByEmail(invalidBo));
    }

    @Test
    @DisplayName("合法用户注销查询")
    @Sql(scripts = "/sql/dummy_customers.sql")
    public void should_void_when_unregister_valid() {
        String validEmail = "oguri_cap@kasamutsu.com";
        CustomerQueryReq queryReq = new CustomerQueryReq() {{
            setEmail(validEmail);
            setPage(1);
            setPageSize(10);
        }};
        customerService.unregisterByEmail(validEmail);
        assertTrue(customerService.query(queryReq).getData().isEmpty());
    }

    @Test
    @DisplayName("非法用户注销查询")
    public void should_error_when_unregister_invalid() {
        String invalidEmail = "goda_katsuhito@joho.com";
        customerService.unregisterByEmail(invalidEmail);
    }
}

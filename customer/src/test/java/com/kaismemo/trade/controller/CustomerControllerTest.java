package com.kaismemo.trade.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kaismemo.trade.config.ConverterTestConfig;
import com.kaismemo.trade.domain.bo.CustomerBo;
import com.kaismemo.trade.domain.req.CustomerQueryReq;
import com.kaismemo.trade.domain.req.CustomerSignupReq;
import com.kaismemo.trade.domain.vo.CustomerVo;
import com.kaismemo.trade.entity.PageData;
import com.kaismemo.trade.entity.Response;
import com.kaismemo.trade.service.CustomerService;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * CustomerController 的接口单元测试
 * <p>
 * 需要用 @Import(ConverterTestConfig.class) 来引入 mapstruct 实现的转换工具 Bean
 *
 * @author reallth
 * @since 1.0.0
 */
@WebMvcTest(CustomerController.class)
@Import(ConverterTestConfig.class)
@DisplayName("CustomerController 接口单元测试")
class CustomerControllerTest {
    @Resource
    private MockMvc mockMvc;

    @Resource
    private ObjectMapper objectMapper;

    @MockBean
    private CustomerService customerService;

    private static final String BASE_URL = "/api/v1/customer";

    @Test
    @DisplayName("合法用户注册")
    public void should_return_ok_when_valid_request() throws Exception {
        CustomerSignupReq validCustomer = new CustomerSignupReq() {{
            setName("Oguri Cap");
            setEmail("oguri_cap@kasamutsu.com");
            setGender("Female");
            setCountry("Japan");
        }};
        doNothing().when(customerService).signup(any(CustomerBo.class));
        MockHttpServletRequestBuilder validHttpReq = post(BASE_URL + "/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(validCustomer))
                .accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(validHttpReq)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200))
                .andExpect(jsonPath("$.message").value("success"));
    }

    @Test
    @DisplayName("非法用户注册")
    public void should_return_error_when_invalid_request() throws Exception {
        CustomerSignupReq invalidCustomer = new CustomerSignupReq() {{
            setName("Goda KatsuHito");
            setEmail("goda_katsuhito@joho.com");
            setGender("Cyber");
            setCountry("Japan");
        }};
        doNothing().when(customerService).signup(any(CustomerBo.class));
        MockHttpServletRequestBuilder validHttpReq = post(BASE_URL + "/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(invalidCustomer))
                .accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(validHttpReq)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(500))
                .andExpect(jsonPath("$.message").value("system error"));
    }

    @Test
    @DisplayName("合法用户查询")
    public void should_return_result_when_query_request_valid() throws Exception {
        CustomerQueryReq validQuery = new CustomerQueryReq() {{
            setName("Oguri Cap");
            setEmail("oguri_cap@kasamutsu.com");
            setGender("Female");
            setCountry("Japan");
            setPage(1);
            setPageSize(10);
        }};
        CustomerVo resultCustomerVo = new CustomerVo() {{
            setName("Oguri Cap");
            setEmail("oguri_cap@kasamutsu.com");
            setGender("Female");
            setCountry("Japan");
        }};
        PageData<CustomerVo> voPageData = new PageData<>(1L, 1L, 1L, 10L, List.of(resultCustomerVo));
        doReturn(voPageData).when(customerService).query(validQuery);
        MockHttpServletRequestBuilder validHttpReq = get(BASE_URL + "/query")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(validQuery))
                .accept(MediaType.APPLICATION_JSON);
        String validResponseJson = objectMapper.writeValueAsString(Response.ok(voPageData));
        mockMvc.perform(validHttpReq)
                .andExpect(status().isOk())
                .andExpect(content().json(validResponseJson));
    }

    @Test
    @DisplayName("非法用户查询")
    public void should_return_error_when_query_request_invalid() throws Exception {
        CustomerQueryReq invalidQuery = new CustomerQueryReq() {{
            setName("Goda KatsuHito");
            setEmail("goda_katsuhito@joho.com");
            setGender("Cyber");
            setCountry("Japan");
            setPage(1);
            setPageSize(10);
        }};
        MockHttpServletRequestBuilder invalidHttpReq = get(BASE_URL + "/query")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(invalidQuery))
                .accept(MediaType.APPLICATION_JSON);
        String invalidResponseJson = objectMapper.writeValueAsString(Response.error(500, "system error"));
        mockMvc.perform(invalidHttpReq)
                .andExpect(status().isOk())
                .andExpect(content().json(invalidResponseJson));
    }
}

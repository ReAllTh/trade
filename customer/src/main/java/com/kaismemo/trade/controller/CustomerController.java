package com.kaismemo.trade.controller;

import com.kaismemo.trade.converter.CustomerConverter;
import com.kaismemo.trade.domain.bo.CustomerBo;
import com.kaismemo.trade.domain.req.CustomerQueryReq;
import com.kaismemo.trade.domain.req.CustomerSignupReq;
import com.kaismemo.trade.domain.vo.CustomerVo;
import com.kaismemo.trade.entity.PageData;
import com.kaismemo.trade.entity.Response;
import com.kaismemo.trade.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

/**
 * customer 接口
 * <p>
 * 对外提供 customer 的注册接口
 *
 * @author reallth
 * @since 1.0.0
 */
@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {

    private final CustomerService customerService;
    private final CustomerConverter customerConverter;

    public CustomerController(CustomerService customerService, CustomerConverter customerConverter) {
        this.customerService = customerService;
        this.customerConverter = customerConverter;
    }

    @PostMapping("/signup")
    public Response<?> signup(@Valid @RequestBody CustomerSignupReq customerSignupReq) {
        CustomerBo bo = customerConverter.toBo(customerSignupReq);
        customerService.signup(bo);
        return Response.ok(null);
    }

    @GetMapping("query")
    public Response<PageData<CustomerVo>> query(@Valid @RequestBody CustomerQueryReq customerQueryReq) {
        PageData<CustomerVo> result = customerService.query(customerQueryReq);
        return Response.ok(result);
    }
}

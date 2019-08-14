package com.daiql.annotationdemo.Controller;

import com.daiql.annotationdemo.bean.Car;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Author daiql
 * Date 2019/8/14 15:34
 * Description 验证测试Controller
 */
@RestController
public class TestController {

    @RequestMapping("/test")
    public String test (@Valid @RequestBody Car car, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            String validMess = fieldError.getDefaultMessage();
            return validMess;
        }
        System.out.println(car.toString());
        return car.toString();
    }

}

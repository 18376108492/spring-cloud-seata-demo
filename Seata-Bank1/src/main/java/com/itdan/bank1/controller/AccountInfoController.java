package com.itdan.bank1.controller;

import com.itdan.bank1.service.IAccountInfoService;
import org.springframework.stereotype.Controller;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author author
 * @since 2020-07-01
 */
@Slf4j
@RestController
public class AccountInfoController {

    @Autowired
    private IAccountInfoService iAccountInfoService;

    @GetMapping("/updateAccountBalance")
    public String updateAccountBalance(@RequestParam("accountNo") String accountNo,@RequestParam("amt") Double amt){

        System.out.println("转账事务测试参数:accountNo-"+accountNo);
        System.out.println("转账事务测试参数:amt-"+amt);
       boolean result=  iAccountInfoService.updateAccountBalance(accountNo,amt);
        if(result){
            return  "转账成功";
        }
        return  "转账失败";
    }
}

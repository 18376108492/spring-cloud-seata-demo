package com.itdan.bank2.controller;

import com.itdan.bank2.service.AccountInfoServiceImpl;
import com.itdan.bank2.service.IAccountInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class Bank2Controller {

    @Autowired
    private IAccountInfoService accountInfoService;

    @GetMapping("/bank2/updateAmt")
    boolean  updateAmt(@RequestParam("amt") Double amt){
        String no="";
       boolean result=   accountInfoService.updateAccountBalance(no,amt);
        System.out.println("result:"+result);
       return  result;
    }


}


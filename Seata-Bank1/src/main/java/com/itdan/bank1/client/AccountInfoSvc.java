package com.itdan.bank1.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Feign 调用层
 */
@FeignClient(name="bank-b",fallback = AccountInfoFallback.class)
public interface AccountInfoSvc {

    /**
     * 通过Feign 远程调用bank2的服务修改李四账户的金额
     * @param amt
     */
    @GetMapping("/bank2/updateAmt")
    boolean  updateAmt(@RequestParam("amt") Double amt);
}

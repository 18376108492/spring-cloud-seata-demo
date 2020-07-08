package com.itdan.bank1.service.impl;


import com.itdan.bank1.client.AccountInfoSvc;
import com.itdan.bank1.mapper.AccountInfoMapper;
import com.itdan.bank1.service.IAccountInfoService;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author author
 * @since 2020-07-01
 */
@Service
@Transactional
@Slf4j
public class AccountInfoServiceImpl implements IAccountInfoService {

    @Autowired
    private AccountInfoMapper accountInfoMapper;

    @Autowired
    private AccountInfoSvc accountInfoSvc;

    /**
     * 模拟张三向李四转账的分布式事务控制
     *
     * @param accountNo
     * @param amt
     */
    @Override
    @GlobalTransactional//开启全局事务
    public boolean updateAccountBalance(String accountNo, Double amt) {

        //例如张三向李四转50元
        //1.先调用bank1 克扣张三账户50元
        int result_1 = accountInfoMapper.updateAccountBalance(accountNo, amt);
        if (result_1 != 1) {
            System.out.println("bank1扣除账户余额出错");

            //throw new RuntimeException("bank1扣除账户余额出错");
           return  false;
        }
        //2.通过feign远程调用bank2 增加李四账户50元
        boolean result_2 = accountInfoSvc.updateAmt(amt);
        //如果出现远程调用失败，回滚事务
        if (!result_2) {
            System.out.println("bank2扣除账户余额出错");
           // throw new RuntimeException("bank2扣除账户余额出错");
            return  false;
        }
        //人为制造错误
        if (amt == 3) {
            System.out.println("人为制造错误");
          //  throw new RuntimeException("bank1 make exception 3");
            return  false;
        }

        return true;
    }
}

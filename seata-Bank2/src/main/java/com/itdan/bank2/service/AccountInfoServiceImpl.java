package com.itdan.bank2.service;


import com.itdan.bank2.mapper.AccountInfoMapper;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author author
 * @since 2020-07-01
 */
@Slf4j
@Service
@Transactional
public class AccountInfoServiceImpl implements IAccountInfoService {

    @Autowired
    private AccountInfoMapper accountInfoMapper;



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

        //制造异常
        if(amt==2){
            throw new RuntimeException("bank1 make exception 2");
        }

        return true;
    }
}

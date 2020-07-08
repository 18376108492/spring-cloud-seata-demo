package com.itdan.bank1.service;
import com.itdan.bank1.model.AccountInfo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author author
 * @since 2020-07-01
 */
public interface IAccountInfoService {

    boolean updateAccountBalance(String  accountNo, Double amt);
}

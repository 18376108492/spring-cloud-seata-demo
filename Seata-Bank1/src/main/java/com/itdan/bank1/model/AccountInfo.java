package com.itdan.bank1.model;

import lombok.Data;

import java.io.Serializable;

@Data
//@TableName("account_info")
public class AccountInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    //@TableId(value = "id", type = IdType.ID_WORKER)
    private Integer id;

    /**
     * 户主姓名
     */
    private String accountName;

    /**
     * 户主卡号
     */
    private String accountNo;

    /**
     * 帐户密码
     */
    private String accountPassword;

    /**
     * 账户余额
     */
    private Double accountBalance;


}

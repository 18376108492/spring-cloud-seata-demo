package com.itdan.bank2.mapper;

import com.itdan.bank2.model.AccountInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author author
 * @since 2020-07-01
 */
@Repository
@Component

public interface AccountInfoMapper extends Mapper<AccountInfo> {
    //向李四转账
    @Update("UPDATE account_info SET account_balance = account_balance + #{amount} WHERE account_no = #{accountNo}")
    int updateAccountBalance(@Param("accountNo") String accountNo, @Param("amount") Double amount);
}

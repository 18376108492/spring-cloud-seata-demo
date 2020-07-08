package com.itdan.bank2.config;


import com.alibaba.druid.pool.DruidDataSource;
import io.seata.rm.datasource.DataSourceProxy;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

//新增DatabaseConfiguration.java，
// Seata的RM通过DataSourceProxy才能在业务代码的事务提交时，
// 通过这个切 入点，与TC进行通信交互、记录undo_log等。
@Configuration
public class DatabaseConfiguration {

        @Value("${spring.datasource.druid.url}")
        private String dbUrl;

        @Value("${spring.datasource.druid.username}")
        private String username;

        @Value("${spring.datasource.druid.password}")
        private String password;

        @Value("${spring.datasource.druid.driver-class-name}")
        private String driverClassName;

        @Bean
        @ConfigurationProperties(prefix = "spring.datasource")
        public DataSource dataSource() {
            DruidDataSource datasource = new DruidDataSource();
            datasource.setUrl(dbUrl);
            datasource.setUsername(username);
            datasource.setPassword(password);
            datasource.setDriverClassName(driverClassName);
            return datasource;
        }

        /**
         * 代理数据源绑定undo_log表的操作
         * @param dataSource
         * @return
         */
        @Bean
        public DataSourceProxy dataSourceProxy(DataSource dataSource) {
            return new DataSourceProxy(dataSource);
        }

        /**
         * Mybatis手动指定sqlSessionFactory所使用的数据源
         * @param dataSourceProxy
         * @return
         * @throws Exception
         */
        @Bean
        public SqlSessionFactory sqlSessionFactoryBean(DataSourceProxy dataSourceProxy) throws Exception {
            SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
            sqlSessionFactoryBean.setDataSource(dataSourceProxy);
            return sqlSessionFactoryBean.getObject();
        }
    }
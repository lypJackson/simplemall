package cn.iocoder.mall.product.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@MapperScan("cn.iocoder.mall.product.dao")
@EnableTransactionManagement(proxyTargetClass = true)
public class DatabaseConfiguration {
}

package com.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

public class MybatisPlusConfig {
  public static void main(String[] args) {



    //全局配置
    GlobalConfig global = new GlobalConfig();
    global.setActiveRecord(true) //是否支持AR模式
        .setAuthor("CXY")//作者名字
        .setBaseColumnList(true)
        .setBaseResultMap(true) //基本的sql返回类型
        .setServiceName("%sService")//service接口的名字首字母是否为I
        .setFileOverride(true) //文件覆盖
        .setIdType(IdType.AUTO)//主键策略
        .setOutputDir("D:/login/src/main/java");//生成路径
    //数据源配置
    DataSourceConfig dsConfig = new DataSourceConfig();
    dsConfig.setDbType(DbType.MYSQL) //设置数据库类型
        .setUrl("jdbc:mysql://localhost:3306/kyuser?useUnicode=true&characterEncoding=utf8&useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=GMT%2B8") //数据库地址
        .setUsername("root")
        .setPassword("root")
        .setDriverName("com.mysql.cj.jdbc.Driver");
    //策略配置
    StrategyConfig stConfig = new StrategyConfig();
    stConfig.setCapitalMode(true)//全局大写命名
        .setNaming(NamingStrategy.underline_to_camel)//数据库映射到实体类的策略
       // .setExclude("section","tb_item_desc","tb_order_item","tb_order")//排除生成的表
        .setRestControllerStyle(true)
        .setColumnNaming(NamingStrategy.underline_to_camel)
        .setEntityLombokModel(true);
    //包名策略
    PackageConfig packConfig = new PackageConfig();
    packConfig.setParent("com")
        .setController("controller")
        .setService("service")
        .setMapper("dao")
        .setEntity("entity")
        .setServiceImpl("serviceImpl")
        .setXml("mybatis.mapper");
//整合配置
    AutoGenerator ag = new AutoGenerator();
    ag.setDataSource(dsConfig)
        .setStrategy(stConfig)
        .setGlobalConfig(global)
        .setPackageInfo(packConfig);
    //执行
    ag.execute();

  }
}

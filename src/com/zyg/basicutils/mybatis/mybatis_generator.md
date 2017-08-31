# Mybatis Generator 使用教程

## 简介

Mybatis Generator用来对Mybatis进行逆向，通过POJO类来生成对应的DAO接口及Mapper文件.

## 依赖

* mybatis.jar
* mybatis-generator-core.jar
* sql.jar

## 配置文件

配置XML文件，在文件中定义如何逆向工程，详细设置见generatorConfig.xml

## 使用

在JDK目录下，打开CMD目录，输入命令，按Enter执行即可完成逆向
> java -jar mybatis-generator-core-xxx.jar -configfile generatorConfig.xml -overwrite


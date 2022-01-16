package com.lyra.mail.generator;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Collections;

public class GeneratorApplication {
    public static void main(String[] args) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/mail_sms", "root", "365373011");

            String sql = "show tables;";
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {

                String tableName = resultSet.getString("Tables_in_mail_sms");

                if (!tableName.contains("qrtz")) {

                    FastAutoGenerator.create("jdbc:mysql://127.0.0.1:3306/mail_sms", "root", "365373011")
                            .globalConfig(builder -> {
                                builder.author("lyra") // 设置作者
                                        .fileOverride() // 覆盖已生成文件
                                        .outputDir("/home/lyra/outPut"); // 指定输出目录
                            })
                            .packageConfig(builder -> {
                                builder.parent("com.lyra.mail.coupon") // 设置父包名 // 设置父包模块名
                                        .pathInfo(Collections.singletonMap(OutputFile.mapperXml, "/home/lyra/outPut/mapper")); // 设置mapperXml生成路径
                            })
                            .strategyConfig(builder -> {
                                builder.addInclude(tableName); // 设置需要生成的表名// 设置过滤表前缀
                            })
                            .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                            .execute();
                }
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }



    }
}

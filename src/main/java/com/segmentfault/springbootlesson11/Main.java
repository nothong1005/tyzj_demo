package com.segmentfault.springbootlesson11;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;

import java.util.Collections;

/**
 * @author 62667
 */
public class Main {

    public static void main(String[] args) {
        String[] fields = { "name", "position", "salary" };
        String table = "employee";

//        测试插入的代码
        String insert = buildInsertSql(table, fields);
        System.out.println(insert);
        String s = "INSERT INTO employee (name, position, salary) VALUES (?, ?, ?)";

        System.out.println(s.equals(insert) ? "测试成功" : "测试失败");

        String select = buildSelectSql(table, fields);
        System.out.println(select);
        System.out.println("SELECT name, position, salary FROM employee".equals(select) ? "测试成功" : "测试失败");

        Log log = LogFactory.getLog(Main.class);

        log.info("start");
    }


    public static String buildInsertSql(String table, String[] fields) {
        StringBuilder sb = new StringBuilder(1024);
        sb.append("INSERT INTO ")
                .append(table)
                .append(" (")
                .append(String.join(", ", fields))
                .append(") VALUES (")
                .append(String.join(", ", Collections.nCopies(fields.length, "?")))
                .append(")");

        return sb.toString();
    }

    static String buildSelectSql(String table, String[] fields) {
        // TODO:
        StringBuilder sb = new StringBuilder(1204);
        sb.append("SELECT ")
                .append(String.join(", ",fields))
                .append(" FROM ")
                .append(table);
        return sb.toString();
    }
}



package org.example.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
    public static Connection getConnection(){
        try {
            Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/dictionary_db",
                    "dic", "11111");
            return con;
        } catch (SQLException e) {
            System.out.println(e.getSQLState());
            System.exit(-1);
        }
        return null;
    }
    public static void initTable() {
        String word = "create table if not exists word ( \n" +
                "             id serial primary key,\n" +
                "             english varchar(20) not null,\n" +
                "             uzbek varchar(20) not null,\n" +
                "             description varchar(80),\n" +
                "             created_date timestamp not null default now());";

        String answer = "create table if not exists answer ( \n" +
                "             id serial primary key,\n" +
                "             word_id int references word(id) not null,\n" +
                "             answer char not null);";


        execute(answer);
        execute(word);

    }
    public static void execute(String sql){
        try(Connection connection = getConnection()) {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }
}

package com.hello;

import com.google.common.base.Optional;
import org.skife.jdbi.v2.DBI;

import java.sql.DriverManager;
import java.util.List;

public class GuavaFun {

    public static void main(String args[]) {
//        DriverManager.registerDriver();
        GuavaFun fun = new GuavaFun();

        Optional<Integer> a = Optional.fromNullable(null);
        Optional<Integer> b = Optional.of(new Integer(10));

        System.out.println(fun.sum(a, b));

//        DBI dbi = new DBI("jdbc:postgres://localhost:5432/rchord",
//                          "rchord", "");

//        UserDAO userDAO = dbi.onDemand(UserDAO.class);
//        userDAO.createUser("First", "Last");

//        List<User> user = userDAO.findById();

//        user.forEach(u -> {
//            System.out.println("ID: " + u.getUser_id());
//        });
    }

    public Integer sum(Optional<Integer> a, Optional<Integer> b) {

        return a.or(new Integer(0)) + b.get();
    }
}
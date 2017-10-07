package com.hello;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

import java.util.List;

public interface UserDAO {

    @SqlUpdate("create table User (user_id int primary key, first_name varchar(100), last_name varchar(100))")
    void createSomethingTable();

    @SqlQuery("select * from public.\"User\" where user_id = :id")
    @Mapper(UserMapper.class)
    User findSingleUserById(@Bind("id") int id);

    @SqlQuery("select * from public.\"User\"")
    @Mapper(UserMapper.class)
    List<User> getAllUsersById();

    @SqlUpdate("UPDATE public.\"User\" " +
                       "SET first_name=:firstName, last_name=:lastName " +
                       "WHERE user_id= :userId")
    void updateUserById(@Bind("userId") int userId, @Bind("firstName") String firstName, @Bind("lastName") String lastName);

    @SqlUpdate("insert into public.\"User\"(first_name, last_name) values(:first_name, :last_name)")
    @GetGeneratedKeys
    int createUser(@Bind("first_name") String firstName, @Bind("last_name") String lastName);

    void close();
}

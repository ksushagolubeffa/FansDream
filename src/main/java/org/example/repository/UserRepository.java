package org.example.repository;

import org.example.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    User findUserByUsername(String username);

    @Query("SELECT u FROM User u WHERE u.username = :username AND u.password = :password")
    User signInUser(@Param("username") String username, @Param("password") String password);


//    @Query("update User user SET user.username = :username WHERE user.uuid = :id")
//    void updateUsername(@Param("username") String username, @Param("id") UUID uuid);
//
//    @Modifying
//    @Query("update User user SET user.password = :password WHERE user.uuid = :id")
//    void updatePassword(@Param("password") String password, @Param("id") UUID uuid);
}

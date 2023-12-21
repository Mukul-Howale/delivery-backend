package com.delivery.Delivery_app.repository;

import com.delivery.Delivery_app.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

//    it is used to check if the pair of user-id and hash password exist or not
    @Query(value ="select * from user  where USER_ID=?1",nativeQuery = true)
    public User checkLogin(Long USER_ID, String PASSWORD);

    @Query(value = "select * from user where email_id=?1",nativeQuery = true)
    User findByEmailId(String email_id);
}

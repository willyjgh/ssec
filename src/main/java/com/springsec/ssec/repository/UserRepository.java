package com.springsec.ssec.repository;

import com.springsec.ssec.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface UserRepository extends JpaRepository<Integer, User> {

    public User findUserByUserName(String username);

}


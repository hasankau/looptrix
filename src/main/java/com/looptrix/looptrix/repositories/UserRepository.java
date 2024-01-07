package com.looptrix.looptrix.repositories;

import com.looptrix.looptrix.Data.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    public User findUserById(long id);

    public User findUserByEmail(String email);
    public User findUserByUserName(String userName);
    @Override
    Page<User> findAll(Pageable pageable);
}

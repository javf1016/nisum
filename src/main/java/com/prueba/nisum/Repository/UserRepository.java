package com.prueba.nisum.Repository;

import com.prueba.nisum.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Boolean existsUserByEmail(@Param("email") String email);
    User findByEmail(@Param("email") String email);
}
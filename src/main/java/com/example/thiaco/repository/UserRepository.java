package com.example.thiaco.repository;

import com.example.thiaco.model.user.User;
import org.apache.xmlbeans.impl.xb.xsdschema.Attribute;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByUsername(String username);

    List<User> findUsersByDeletedIsFalse();
}

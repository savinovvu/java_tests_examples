package com.example.testexample.repository;

import com.example.testexample.model.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

  List<User> findAllByFirstName(String firstName);
}

package com.example.testexample.service;

import com.example.testexample.model.User;
import com.example.testexample.repository.UserRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository repository;

  public List<User> findAll() {
    return repository.findAll();
  }

  public User findById(Integer id) {
    return repository.findById(id).orElse(null);
  }
}

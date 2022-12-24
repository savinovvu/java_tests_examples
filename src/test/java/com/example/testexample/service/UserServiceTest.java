package com.example.testexample.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.BDDMockito.given;

import com.example.testexample.model.User;
import com.example.testexample.repository.UserRepository;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

  @Mock
  private UserRepository repository;

  private UserService subject;

  @BeforeEach
  public void setUp() {
    subject = new UserService(repository);
  }

  @Test
  public void findAll_success() {
    int id1 = 1;
    int id2 = 2;
    given(repository.findAll()).willReturn(
        Arrays.asList(new User().setId(id1), new User().setId(id2)));
    List<User> all = subject.findAll();

    assertThat(all).size().isEqualTo(2);
    assertThat(all.get(0).getId()).isEqualTo(id1);
    assertThat(all.get(1).getId()).isEqualTo(id2);


  }

  @Test
  public void findById_success() {
    int id = 1;
    User user = new User().setId(id);
    given(repository.findById(id)).willReturn(Optional.of(user));
    User byId = subject.findById(id);

    assertThat(byId.getId()).isEqualTo(id);
  }

  @Test
  public void findAll_exception() {
    String message = "this is exception message";
    given(repository.findAll()).willAnswer(invocation -> {
      throw new RuntimeException(message);
    });

    assertThatThrownBy(() -> {
      subject.findAll();
    }).isInstanceOf(RuntimeException.class).hasMessageContaining(message);
  }

}

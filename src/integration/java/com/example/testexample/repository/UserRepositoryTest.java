package com.example.testexample.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.testexample.model.User;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(SpringExtension.class)
@DataJpaTest(showSql = true)
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserRepositoryTest {

  @Autowired
  protected TestEntityManager em;

  @Autowired
  private UserRepository subject;

  @Test
  public void contextLoads() {
    new User();
    assertThat(em).isNotNull();
    assertThat(subject).isNotNull();
  }

  @Test
  public void hasEmptyConstructor() {
    new User();
  }

  @Test
  public void findByUsername() {
    String testUsername = "test";
    String testUsername2 = "test2";
    User testUser = new User().setFirstName(testUsername);
    User testUser2 = new User().setFirstName(testUsername);
    User testUserOther = new User().setFirstName(testUsername2);
    em.persistAndFlush(testUser);
    em.persistAndFlush(testUser2);
    em.persistAndFlush(testUserOther);
    em.clear();
    List<User> users = subject.findAllByFirstName(testUsername);
    assertThat(users).size().isEqualTo(2);
    assertThat(users.get(0).getFirstName()).isEqualTo(testUsername);
    assertThat(users.get(1).getFirstName()).isEqualTo(testUsername);
  }

  @Test
  public void createUser() {
    String first = "test";
    String last = "test2";
    User testUser = new User().setFirstName(first).setLastName(last);
    User savedUser = subject.saveAndFlush(testUser);
    em.clear();
    User actualUser = em.find(User.class, savedUser.getId());
    assertThat(actualUser.getFirstName()).isEqualTo(first);
    assertThat(actualUser.getLastName()).isEqualTo(last);
  }

  @Test
  public void updateUser() {
    String first = "test";
    String last = "test2";
    String newFirst = "testzzz";
    User testUser = new User().setFirstName(first).setLastName(last);
    em.persistAndFlush(testUser);
    em.clear();
    testUser.setFirstName(newFirst);
    subject.saveAndFlush(testUser);
    em.clear();
    User actualUser = em.find(User.class, testUser.getId());

    assertThat(actualUser.getFirstName()).isEqualTo(newFirst);
    assertThat(actualUser.getLastName()).isEqualTo(last);

  }


}

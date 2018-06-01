package io.pivotal.literx;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import io.pivotal.literx.domain.User;
import java.util.List;
import org.junit.Test;

public class Part00WithoutReactiveTest {

  Part00WithoutReactive workshop = new Part00WithoutReactive();

  @Test
  public void shouldFindAllUsersAsStarWarsCharacter() {

    final List<User> results = workshop.findAllUsersAsStarWarsCharacter();
    assertThat(results.size(), is(4));
    assertThat(results.get(0), is(new User("swhite", "Obi Wan", "White")));
    assertThat(results.get(1), is(new User("jpinkman", "Anakin", "Pinkman")));
    assertThat(results.get(2), is(new User("wwhite", "Luke", "White")));
    assertThat(results.get(3), is(new User("sgoodman", "Jar Jar", "Goodman")));
  }


}
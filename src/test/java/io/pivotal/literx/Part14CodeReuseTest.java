package io.pivotal.literx;

import io.pivotal.literx.domain.User;
import io.pivotal.literx.repository.ReactiveUserRepository;
import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class Part14CodeReuseTest {

  Part14CodeReuse workshop = new Part14CodeReuse();
  ReactiveUserRepository reactiveUserRepository = new ReactiveUserRepository();

  @Test
  public void xxx() {
    final Flux<User> users = reactiveUserRepository.getAll();

    final Flux<User> composed = workshop.takeMoreAndMore(users);
    StepVerifier.create(composed)
        .expectNextCount(1)
        .expectComplete()
        .verify();

//    StepVerifier.create(composed)
//        .expectNextCount(2)
//        .expectComplete()
//        .verify();
//
//    StepVerifier.create(composed)
//        .expectNextCount(3)
//        .expectComplete()
//        .verify();
//
//    StepVerifier.create(composed)
//        .expectNextCount(4)
//        .expectComplete()
//        .verify();
  }


}
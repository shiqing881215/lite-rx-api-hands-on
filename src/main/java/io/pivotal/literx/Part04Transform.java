package io.pivotal.literx;

import io.pivotal.literx.domain.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

/**
 * Learn how to transform values.
 *
 * @author Sebastien Deleuze
 */
public class Part04Transform {

  // ========================================================================================

  // TODO Switch user username, firstName and lastName to uppercase
  Mono<User> capitalizeOne(Mono<User> mono) {
    // map just does a transformation, user -> user
    return mono.map(user -> {
      return new User(user.getUsername().toUpperCase(),user.getFirstName().toUpperCase(), user.getLastName().toUpperCase());
    });
  }

  // ========================================================================================

  // TODO Switch the users username, firstName and lastName to uppercase
  Flux<User> capitalizeMany(Flux<User> flux) {
    // map operation is same for both Mono and Flux
    return flux.map(user -> new User(user.getUsername().toUpperCase(),user.getFirstName().toUpperCase(), user.getLastName().toUpperCase()));
  }

  // ========================================================================================

  // TODO Switch the users username, firstName and lastName to uppercase using #asyncUserToUppercase
  Flux<User> asyncCapitalizeMany(Flux<User> flux) {

    // let each user to be transformed individually(Async) by asyncUserToUppercase, then combine the result back into one Flux
    // https://projectreactor.io/docs/core/release/api/reactor/core/publisher/Flux.html#flatMap-java.util.function.Function-
    return flux.flatMap(user -> asyncUserToUppercase(user));
  }

  // ========================================================================================

  // TODO Filter out user with firstName starting with the letter 'S'.
  Flux<User> filterNameStartingWithS(Flux<User> flux) {

    return flux.filter(user -> !user.getFirstName().startsWith("S"));
  }

  Mono<User> asyncUserToUppercase(User u) {
    return Mono.just(
        new User(
            u.getUsername().toUpperCase(),
            u.getFirstName().toUpperCase(),
            u.getLastName().toUpperCase()))
        .delayElement(Duration.ofMillis(100));
  }
}

package io.pivotal.literx;

import io.pivotal.literx.domain.User;
import io.pivotal.literx.repository.ReactiveUserRepository;
import io.pivotal.literx.repository.ReactiveStarWarsUsersRepository;
import java.util.function.Consumer;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Hooks;

/**
 * Learn how to debug Reactor code.
 *
 * @author Julien Hoarau
 * @see Hooks#onOperatorDebug()
 * @see Flux#checkpoint()
 * @see Flux#doOnNext(Consumer)
 */
public class Part12Debugging {

  ReactiveUserRepository userRepository = new ReactiveUserRepository();
  ReactiveStarWarsUsersRepository reactiveStarWarsUsersRepository = new ReactiveStarWarsUsersRepository();

  Flux<User> starWarsifyUsers() {
    return reactiveStarWarsUsersRepository.starWarsify(userRepository.getAll());
  }

  // ========================================================================================

  // TODO Return a Flux with all users stored in the repository that prints automatically logs for
  // all Reactive Streams signals
  Flux<User> fluxWithLog() {
    return null;
  }

  // ========================================================================================

  // TODO Return a Flux with all users stored in the repository that prints "Starring:" on
  // subscribe, "firstname lastname" for all values and "The end!" on complete
  Flux<User> fluxWithDoOnPrintln() {

    return null;
  }
}
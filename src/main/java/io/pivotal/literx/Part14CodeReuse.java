package io.pivotal.literx;

import io.pivotal.literx.domain.User;
import java.util.function.Function;
import reactor.core.publisher.Flux;

/**
 * Learn how to reuse operators
 *
 * @author Julien Hoarau
 * @see Flux#transform(Function)
 * @see Flux#compose(Function)
 */
public class Part14CodeReuse {

  // TODO Modify the input flux to return more and more users every time
  // the flux is subscribed
  // should return 1 user the first time
  // 2 the second time
  // 3 the third time...
  public Flux<User> takeMoreAndMore(Flux<User> users) {
    return users;
  }

}

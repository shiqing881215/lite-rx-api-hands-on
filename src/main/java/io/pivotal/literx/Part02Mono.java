package io.pivotal.literx;

import reactor.core.publisher.Mono;
import reactor.util.annotation.Nullable;

/**
 * Learn how to create Mono instances.
 *
 * @author Sebastien Deleuze
 * @see <a
 *     href="http://projectreactor.io/docs/core/release/api/reactor/core/publisher/Mono.html">Mono
 *     Javadoc</a>
 */
public class Part02Mono {

  // ========================================================================================

  // TODO Return an empty Mono
  Mono<String> emptyMono() {

    return Mono.empty();
  }

  // ========================================================================================

  // TODO Return an empty Mono from nullable input value
  Mono<String> emptyMonoFromNull(@Nullable String input) {

    return Mono.justOrEmpty(input);
  }

  // ========================================================================================

  // TODO Return a Mono that never emits any signal
  // https://stackoverflow.com/questions/48273301/when-to-use-mono-never
  Mono<String> monoWithNoSignal() {

    return Mono.never();
  }

  // ========================================================================================

  // TODO Return a Mono that contains a "foo" value
  Mono<String> fooMono() {

    return Mono.just("foo");
  }

  // ========================================================================================

  // TODO Create a Mono that emits an IllegalStateException
  Mono<String> errorMono() {

    return Mono.error(new IllegalStateException());
  }
}

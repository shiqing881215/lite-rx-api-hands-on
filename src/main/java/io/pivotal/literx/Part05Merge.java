package io.pivotal.literx;

import io.pivotal.literx.domain.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Learn how to merge flux.
 *
 * @author Sebastien Deleuze
 * @author Julien Hoarau
 */
public class Part05Merge {

  // ========================================================================================

  // TODO Merge flux1 and flux2 values (order doesn't matter)
  Flux<User> mergeFluxWithInterleave(Flux<User> flux1, Flux<User> flux2) {

    // Both works
    return Flux.merge(flux1, flux2);
//    return flux1.mergeWith(flux2);
  }

  // ========================================================================================

  // TODO Merge flux1 and flux2 values in order (flux1 values and then flux2 values)
  Flux<User> mergeFluxWithNoInterleave(Flux<User> flux1, Flux<User> flux2) {
    // Both works
    return Flux.concat(flux1, flux2);
//    return flux1.concatWith(flux2);
  }

  // ========================================================================================

  // TODO Create a Flux containing the value of mono1 then the value of mono2
  Flux<User> createFluxFromMultipleMono(Mono<User> mono1, Mono<User> mono2) {

    return Flux.concat(mono1, mono2);
  }

  // Another one to see is zipWith
  // https://projectreactor.io/docs/core/release/api/reactor/core/publisher/Flux.html#zipWith-org.reactivestreams.Publisher-
  // wait for both publisher to emit one element and zip them together into a tuple
}

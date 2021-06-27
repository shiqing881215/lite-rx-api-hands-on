package io.pivotal.literx;

import io.pivotal.literx.domain.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * Learn how to use various other operators.
 *
 * @author Sebastien Deleuze
 */
public class Part08OtherOperations {

  // ========================================================================================

  // TODO Create a Flux of user from Flux of username, firstName and lastName.
  Flux<User> userFluxFromStringFlux(
      Flux<String> usernameFlux, Flux<String> firstNameFlux, Flux<String> lastNameFlux) {
    // zip returns Tuple<Publisher1, Publisher2, Publisher3>
    return Flux.zip(usernameFlux, firstNameFlux, lastNameFlux)
            .map(tuple -> new User(tuple.getT1(), tuple.getT2(), tuple.getT3()));
  }

  // ========================================================================================

  // TODO Return the mono which returns its value faster
  Mono<User> useFastestMono(Mono<User> mono1, Mono<User> mono2) {
    // Pick the first Mono which emits any signal
    return Mono.firstWithSignal(mono1, mono2);
  }

  // ========================================================================================

  // TODO Return the flux which returns the first value faster
  Flux<User> useFastestFlux(Flux<User> flux1, Flux<User> flux2) {

    return Flux.firstWithSignal(flux1, flux2);
  }

  // ========================================================================================

  // TODO Convert the input Flux<User> to a Mono<Void> that represents the complete signal of the
  // flux
  Mono<Void> fluxCompletion(Flux<User> flux) {
    // Return a Mono<Void> that completes when this Flux completes
    return flux.then();
  }

  // ========================================================================================

  // TODO Return a valid Mono of user for null input and non null input user (hint: Reactive Streams
  // do not accept null values)
  Mono<User> nullAwareUserToMono(User user) {
    // Create a new Mono that emits the specified item if non null otherwise only emits onComplete
    // this creates empty Mono when input is empty
    return Mono.justOrEmpty(user);
  }

  // ========================================================================================

  // TODO Return the same mono passed as input parameter, expect that it will emit User.SKYLER when
  // empty
  Mono<User> emptyToSkyler(Mono<User> mono) {
    // this adds default value when input is empty
    return mono.defaultIfEmpty(User.SKYLER);
  }

  // TODO Convert the input Flux<User> to a Mono<List<User>> containing list of collected flux values
  Mono<List<User>> fluxCollection(Flux<User> flux) {
    // Collect all elements emitted by this Flux into a List that is emitted by the resulting Mono when this sequence completes.
    return flux.collectList();
  }
}

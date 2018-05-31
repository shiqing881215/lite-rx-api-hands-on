package io.pivotal.literx;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;
import reactor.test.StepVerifier;

/**
 * Learn about publishOn/subscribeOn.
 *
 * @author Julien Hoarau
 */
public class Part13ExecutionContextTest {
  private static final Logger log = LoggerFactory.getLogger(Part13ExecutionContextTest.class);

  private final Scheduler blue = Schedulers.newSingle("blue");
  private final Scheduler red = Schedulers.newSingle("red");
  private final Scheduler green = Schedulers.newSingle("green");
  private final Scheduler yellow = Schedulers.newSingle("yellow");


  @Test
  public void readingTeaLeaves() {
    final Flux<Integer> source = Flux.range(0, 10)
        .map(integer -> integer * 2)
        .subscribeOn(blue)
        .publishOn(green)
        .map(integer -> integer * 4)
        .flatMap(this::zeroIfEven)
        .subscribeOn(red);


    StepVerifier.create(source)
        .expectNextCount(10)
        .verifyComplete();
  }

  private Mono<Integer> zeroIfEven(int number) {
    return Mono.just(number % 2 == 0 ? 0 : number)
        .subscribeOn(yellow);
  }

}

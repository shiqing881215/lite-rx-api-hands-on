package io.pivotal.literx;

import org.junit.Test;
import reactor.test.StepVerifier;

/**
 * Learn about publishOn/subscribeOn.
 *
 * @author Julien Hoarau
 */
public class Part13ExecutionContextTest {
  private Part13ExecutionContext workshop = new Part13ExecutionContext();

  @Test
  public void findExecutionContextForEachOperator() {
    StepVerifier.create(workshop.findWhichExecutionContext())
        .expectNextCount(10)
        .verifyComplete();
  }

  @Test
  public void shouldEmitThreadNames() {
    StepVerifier.create(workshop.usePublishSubscribe())
        .expectNext("blue-1")
        .expectNext("red-2")
        .expectNext("yellow-3")
        .expectNext("green-4")
        .verifyComplete();
  }
}

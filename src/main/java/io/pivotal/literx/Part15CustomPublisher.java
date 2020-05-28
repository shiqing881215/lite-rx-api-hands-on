package io.pivotal.literx;

import java.time.Duration;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import reactor.core.publisher.Flux;

/**
 * Learn how to create custom publisher
 *
 * @author Julien Hoarau
 * @see Flux#create(Consumer)
 * @see Flux#generate(Consumer)
 * @see Flux#push(Consumer)
 */
public class Part15CustomPublisher {

  /**
   * TODO Reimplement Flux.range()
   *
   * @see Flux#range(int, int)
   */
  public Flux<Integer> range(int start, int count) {
    AtomicInteger current = new AtomicInteger(start);
    return Flux.generate(sink -> {
      if (current.get() == start + count) {
        sink.complete();
        return;
      }

      sink.next(current.getAndIncrement());
    });
  }

  /**
   * TODO Reimplement Flux.interval(Duration.ofMillis(100))
   *
   * @see Flux#interval(Duration)
   */
  public Flux<Integer> interval() {


    return Flux.create(sink -> {
      Timer timer = new Timer();
      AtomicInteger current = new AtomicInteger(0);

      sink.onCancel(timer::cancel);
      sink.onDispose(timer::cancel);

      timer.schedule(new TimerTask() {
        @Override
        public void run() {
          sink.next(current.getAndIncrement());
        }
      }, 0, 100);
    });
  }
}

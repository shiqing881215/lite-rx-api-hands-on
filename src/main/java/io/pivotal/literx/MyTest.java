package io.pivotal.literx;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.time.LocalTime;

public class MyTest {
    public static void main(String[] args) {
        /*
        This prints something like
        -- Mapping Flux elements --
        21:36:36.554 [main] DEBUG reactor.util.Loggers - Using Slf4j logging framework
        -----------
        1 - main
        2 - main
        -----------
        2 - main
        3 - main
        4 - main
        5 - main
        -----------
        3 - main
        4 - main
        5 - main
        6 - main
        7 - main
        8 - main

        This does not have parallelism. For all the subscription, it uses the same thread main
        Without parallelism it will wait for at least one to complete before it starts mapping more source elements
         */
//        System.out.println("-- Mapping Flux elements --");
//        Flux.just(1, 2, 3)
//                .flatMap( integer -> {
//                    System.out.println("-----------");
//                    return Flux.range(integer, integer*2);
//                }, 3)
//                .subscribe(e -> System.out.println(e+" - "+Thread.currentThread().getName()));

        /*
         This prints something like
         -- Mapping Flux elements --
         -----------
         -----------
         -----------
         1 - myThread-1 - 21:39:36.666286
         2 - myThread-1 - 21:39:36.674648
         3 - myThread-1 - 21:39:36.674745
         4 - myThread-1 - 21:39:36.674808
         5 - myThread-1 - 21:39:36.674864
         3 - myThread-1 - 21:39:36.674942
         4 - myThread-1 - 21:39:36.675001
         5 - myThread-1 - 21:39:36.675060
         6 - myThread-1 - 21:39:36.675118
         7 - myThread-1 - 21:39:36.675175
         8 - myThread-1 - 21:39:36.675233
         2 - myThread-1 - 21:39:36.675304

         You can see 3 ----------- together, that means 3 elements from the source Flux are processed together
         */
//        System.out.println("-- Mapping Flux elements --");
//        Flux.just(1, 2, 3)
//                .flatMap(integer -> {
//                    System.out.println("-----------");
//                    return Flux.range(integer, integer * 2)
//                            .subscribeOn(Schedulers.newParallel("myThread", 8));
//                }, 3)
//                .subscribe(e -> System.out.println(e + " - " + Thread.currentThread().getName()+" - "+ LocalTime.now()));


        /*
        This prints out something like
        -- Mapping Flux elements --
        -----------
        1 - myThread-1 - 21:42:24.765383
        2 - myThread-1 - 21:42:24.775583
        -----------
        2 - myThread-2 - 21:42:24.776230
        3 - myThread-2 - 21:42:24.776320
        4 - myThread-2 - 21:42:24.776388
        5 - myThread-2 - 21:42:24.776445
        -----------
        3 - myThread-3 - 21:42:24.776948
        4 - myThread-3 - 21:42:24.777038
        5 - myThread-3 - 21:42:24.777102
        6 - myThread-3 - 21:42:24.777223
        7 - myThread-3 - 21:42:24.777291
        8 - myThread-3 - 21:42:24.777351

        The difference between this one and the above is we pass 1 as concurrency to the 2nd param in flatMap()
        Notice, this is NOT how we control parallelism, flatMap itself never deal with parallelism.
        This defines how many internal publisher can be subscribe in parallel.
        In this case, even though we have multiple threads available, but each time we can only subscribe one
        internal publisher, we still not getting true parallelism
         */
//        System.out.println("-- Mapping Flux elements --");
//        Flux.just(1, 2, 3)
//                .flatMap(integer -> {
//                    System.out.println("-----------");
//                    return Flux.range(integer, integer * 2)
//                            .subscribeOn(Schedulers.newParallel("myThread", 8));
//                }, 1)
//                .subscribe(e -> System.out.println(e + " - " + Thread.currentThread().getName() + " - " + LocalTime.now()));

        /*
        This prints out
        -- Mapping Flux elements --
        -----------
        -----------
        2 - myThread-2 - 22:00:43.553495
        1 - myThread-2 - 22:00:43.561745
        2 - myThread-2 - 22:00:43.561876
        -----------
        3 - myThread-2 - 22:00:43.562389
        4 - myThread-2 - 22:00:43.562469
        5 - myThread-2 - 22:00:43.562529
        3 - myThread-3 - 22:00:43.562603
        4 - myThread-3 - 22:00:43.562672
        5 - myThread-3 - 22:00:43.562726
        6 - myThread-3 - 22:00:43.562779
        7 - myThread-3 - 22:00:43.562830
        8 - myThread-3 - 22:00:43.562881

        Similar as above, we change concurrency for flatMap to 2. So we can have 2 run in parallel initially.
        Then one of finishes, we can subscribe to the next Publisher
         */
//        System.out.println("-- Mapping Flux elements --");
//        Flux.just(1, 2, 3)
//                .flatMap(integer -> {
//                    System.out.println("-----------");
//                    return Flux.range(integer, integer * 2)
//                            .subscribeOn(Schedulers.newParallel("myThread", 8));
//                }, 2)
//                .subscribe(e -> {
//                    System.out.println(e + " - " + Thread.currentThread().getName() + " - " + LocalTime.now());
//                });

        /*
        We can potentially use other types of Schedulers
        Here I'm using Schedulers.newSingle() which creates a new thread for each subscription run.
        We set flatMap concurrency to 3, so we also have 3 runs together in parallel

        This prints out
        -- Mapping Flux elements --
        -----------
        -----------
        -----------
        3 - foo-3 - 22:07:32.942310
        1 - foo-3 - 22:07:32.950068
        2 - foo-3 - 22:07:32.950166
        2 - foo-3 - 22:07:32.950251
        3 - foo-3 - 22:07:32.950313
        4 - foo-3 - 22:07:32.950373
        5 - foo-3 - 22:07:32.950431
        4 - foo-3 - 22:07:32.950502
        5 - foo-3 - 22:07:32.950558
        6 - foo-3 - 22:07:32.950613
        7 - foo-3 - 22:07:32.950669
        8 - foo-3 - 22:07:32.950724
         */

        System.out.println("-- Mapping Flux elements --");
        Flux.just(1, 2, 3)
                .flatMap(integer -> {
                    System.out.println("-----------");
                    return Flux.range(integer, integer * 2)
                            .subscribeOn(Schedulers.newSingle("foo"));
                }, 3)
                .subscribe(e -> {
                    System.out.println(e + " - " + Thread.currentThread().getName() + " - " + LocalTime.now());
                });
    }
}

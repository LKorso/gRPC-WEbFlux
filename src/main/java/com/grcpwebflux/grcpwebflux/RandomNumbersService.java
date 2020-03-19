package com.grcpwebflux.grcpwebflux;

import org.reactivestreams.Publisher;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.Random;

@Service
public class RandomNumbersService {
    public Publisher<Integer> generateNumbers(int delay) {
        return Flux.fromStream(new Random().ints(0, 1000).boxed())
                .delayElements(Duration.ofSeconds(delay));
    }
}

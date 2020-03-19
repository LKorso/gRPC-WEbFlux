package com.grcpwebflux.grcpwebflux;

import org.reactivestreams.Publisher;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RandomNumberController {

    private final RandomNumbersService randomNumbersService;

    public RandomNumberController(RandomNumbersService randomNumbersService) {
        this.randomNumbersService = randomNumbersService;
    }

    @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE, value = "/numbers/{delay}")
    public Publisher<Integer> numbersPublisher(@PathVariable int delay) {
        return randomNumbersService.generateNumbers(delay);
    }

}

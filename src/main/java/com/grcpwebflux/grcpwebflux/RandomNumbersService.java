package com.grcpwebflux.grcpwebflux;

import com.grcpwebflux.server.NumberBorders;
import com.grcpwebflux.server.RandomNumber;
import com.grcpwebflux.server.RandomNumberServiceGrpc;
import io.grpc.stub.StreamObserver;
import org.reactivestreams.Processor;
import org.reactivestreams.Publisher;
import org.springframework.stereotype.Service;
import reactor.core.publisher.EmitterProcessor;

@Service
public class RandomNumbersService {

    private final RandomNumberServiceGrpc.RandomNumberServiceStub randomNumberStub;

    public RandomNumbersService(RandomNumberServiceGrpc.RandomNumberServiceStub randomNumberStub) {
        this.randomNumberStub = randomNumberStub;
    }

    public Publisher<Integer> generateNumbers(int delay) {
        NumberBorders request = NumberBorders.newBuilder()
                .setSize(50)
                .setMinValue(0)
                .setMaxValue(100)
                .setDelay(delay)
                .build();
        Processor<Integer, Integer> processor = EmitterProcessor.create();
        randomNumberStub.produce(request, new StreamObserver<>() {
            @Override
            public void onNext(RandomNumber value) {
                processor.onNext(value.getValue());
            }

            @Override
            public void onError(Throwable t) {
                processor.onError(t);
            }

            @Override
            public void onCompleted() {
                processor.onComplete();
            }
        });
        return processor;
    }

}

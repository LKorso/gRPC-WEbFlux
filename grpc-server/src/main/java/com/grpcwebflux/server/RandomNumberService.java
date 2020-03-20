package com.grpcwebflux.server;

import com.grcpwebflux.server.NumberBorders;
import com.grcpwebflux.server.RandomNumber;
import com.grcpwebflux.server.RandomNumberServiceGrpc;
import io.grpc.stub.StreamObserver;

import java.time.Duration;
import java.util.Random;

public class RandomNumberService extends RandomNumberServiceGrpc.RandomNumberServiceImplBase {

    @Override
    public void produce(NumberBorders borders, StreamObserver<RandomNumber> responseObserver) {
        for (int number : new Random().ints(borders.getSize(), borders.getMinValue(), borders.getMaxValue()).toArray()) {
            System.out.println("Generated number: " + number);
            responseObserver.onNext(RandomNumber.newBuilder().setValue(number).build());
            try {
                Thread.sleep(Duration.ofSeconds(borders.getDelay()).toMillis());
            } catch (InterruptedException e) {
                System.out.println("Error while delaying number producing: " + e.getMessage());
            }
        }
        System.out.println("" + borders.getSize() + " were generated");
    }

}

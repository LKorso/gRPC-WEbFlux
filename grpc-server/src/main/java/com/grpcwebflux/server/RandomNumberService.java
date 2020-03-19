package com.grpcwebflux.server;

import com.grcpwebflux.server.NumberBorders;
import com.grcpwebflux.server.RandomNumber;
import com.grcpwebflux.server.RandomNumberServiceGrpc;
import io.grpc.stub.StreamObserver;

import java.util.Random;

public class RandomNumberService extends RandomNumberServiceGrpc.RandomNumberServiceImplBase {

    private Random random = new Random();

    @Override
    public StreamObserver<NumberBorders> produce(StreamObserver<RandomNumber> responseObserver) {
        return new StreamObserver<>() {
            @Override
            public void onNext(NumberBorders borders) {
                int randomNumber = random.nextInt(borders.getMaxValue() - borders.getMinValue() + 1) + borders.getMinValue();
                RandomNumber result = RandomNumber.newBuilder().setValue(randomNumber).build();
                responseObserver.onNext(result);
            }

            @Override
            public void onError(Throwable t) {
                System.out.println("Error in RandomNumberService : " + t.getMessage());
            }

            @Override
            public void onCompleted() {
                responseObserver.onCompleted();
            }
        };
    }

}

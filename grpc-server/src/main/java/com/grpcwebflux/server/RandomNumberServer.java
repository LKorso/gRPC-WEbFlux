package com.grpcwebflux.server;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class RandomNumberServer {
    public static void main(String[] args) throws InterruptedException, IOException {
        Server server = ServerBuilder
                .forPort(8081)
                .addService(new RandomNumberService())
                .build();
        server.start();
        server.awaitTermination();
    }
}

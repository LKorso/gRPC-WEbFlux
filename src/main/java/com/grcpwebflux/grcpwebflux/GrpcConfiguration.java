package com.grcpwebflux.grcpwebflux;

import com.grcpwebflux.server.RandomNumberServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GrpcConfiguration {

    @Bean
    public RandomNumberServiceGrpc.RandomNumberServiceStub randomNumberStub() {
        ManagedChannel channel = ManagedChannelBuilder
                .forAddress("localhost", 8081)
                .usePlaintext()
                .build();
        return RandomNumberServiceGrpc.newStub(channel);
    }

}

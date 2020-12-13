package com.example.demo.controllers.grpc;

import com.example.order.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class OrderGrpcController extends orderServiceGrpc.orderServiceImplBase{
    String url = "localhost";

    @Override
    public void getOrder(GetRequestOrder request, StreamObserver<GetResponseOrder> responseStreamObserver) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress(url, 9094)
                .usePlaintext()
                .build();
        orderServiceGrpc.orderServiceBlockingStub stub = orderServiceGrpc.newBlockingStub(channel);
        GetResponseOrder response = stub.getOrder(request);
        channel.shutdown();
        responseStreamObserver.onNext(response);
        responseStreamObserver.onCompleted();
    }

    @Override
    public void getOrderedThing(GetRequestOrderedItem request, StreamObserver<GetResponseOrderedItem> responseStreamObserver) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress(url, 9094)
                .usePlaintext()
                .build();
        orderServiceGrpc.orderServiceBlockingStub stub = orderServiceGrpc.newBlockingStub(channel);
        GetResponseOrderedItem response = stub.getOrderedThing(request);
        channel.shutdown();
        responseStreamObserver.onNext(response);
        responseStreamObserver.onCompleted();
    }

    @Override
    public void create(CreateRequest request, StreamObserver<CreateResponse> responseObserver) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress(url, 9094)
                .usePlaintext()
                .build();
        orderServiceGrpc.orderServiceBlockingStub stub = orderServiceGrpc.newBlockingStub(channel);
        CreateResponse response = stub.create(request);
        channel.shutdown();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
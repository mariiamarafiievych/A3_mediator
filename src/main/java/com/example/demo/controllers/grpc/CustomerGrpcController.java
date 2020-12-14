package com.example.demo.controllers.grpc;

import com.example.customer.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class CustomerGrpcController extends customerServiceGrpc.customerServiceImplBase {
    String url = "customer-service-new";
    @Override
    public void get(GetRequest request, StreamObserver<GetResponse> responseStreamObserver) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress(url, 9090)
                .usePlaintext()
                .build();
        customerServiceGrpc.customerServiceBlockingStub stub = customerServiceGrpc.newBlockingStub(channel);
        GetResponse response = stub.get(request);
        channel.shutdown();
        responseStreamObserver.onNext(response);
        responseStreamObserver.onCompleted();
    }

    @Override
    public void create(CreateRequest request, StreamObserver<CreateResponse> responseObserver) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress(url, 9090)
                .usePlaintext()
                .build();
        customerServiceGrpc.customerServiceBlockingStub stub = customerServiceGrpc.newBlockingStub(channel);
        CreateResponse response = stub.create(request);
        channel.shutdown();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void delete(DeleteRequest request, StreamObserver<DeleteResponse> responseObserver) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress(url, 9090)
                .usePlaintext()
                .build();
        customerServiceGrpc.customerServiceBlockingStub stub = customerServiceGrpc.newBlockingStub(channel);
        DeleteResponse response = stub.delete(request);
        channel.shutdown();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
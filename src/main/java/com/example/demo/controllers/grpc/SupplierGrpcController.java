package com.example.demo.controllers.grpc;

import com.example.seller.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class SupplierGrpcController extends supplierServiceGrpc.supplierServiceImplBase {
    String url = "supplier-service-new";

    @Override
    public void getSuppliers(GetRequestSupplier request, StreamObserver<GetResponseSupplier> responseStreamObserver) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress(url, 9093)
                .usePlaintext()
                .build();
        supplierServiceGrpc.supplierServiceBlockingStub stub = supplierServiceGrpc.newBlockingStub(channel);
        GetResponseSupplier response = stub.getSuppliers(request);
        channel.shutdown();
        responseStreamObserver.onNext(response);
        responseStreamObserver.onCompleted();
    }

    @Override
    public void getItems(GetRequestItem request, StreamObserver<GetResponseItem> responseStreamObserver) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress(url, 9093)
                .usePlaintext()
                .build();
        supplierServiceGrpc.supplierServiceBlockingStub stub = supplierServiceGrpc.newBlockingStub(channel);
        GetResponseItem response = stub.getItems(request);
        channel.shutdown();
        responseStreamObserver.onNext(response);
        responseStreamObserver.onCompleted();
    }

    @Override
    public void create(CreateRequest request, StreamObserver<CreateResponse> responseObserver) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress(url, 9093)
                .usePlaintext()
                .build();
        supplierServiceGrpc.supplierServiceBlockingStub stub = supplierServiceGrpc.newBlockingStub(channel);
        CreateResponse response = stub.create(request);
        channel.shutdown();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
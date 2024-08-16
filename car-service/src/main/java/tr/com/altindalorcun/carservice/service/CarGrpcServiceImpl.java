package tr.com.altindalorcun.carservice.service;

import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;
import tr.com.altindalorcun.carservice.CarDto;
import tr.com.altindalorcun.carservice.CarServiceGrpc;
import tr.com.altindalorcun.carservice.Id;
import tr.com.altindalorcun.carservice.exception.CarNotFoundException;
import tr.com.altindalorcun.carservice.repository.CarRepository;

import java.util.UUID;

@GrpcService
@Slf4j
public class CarGrpcServiceImpl extends CarServiceGrpc.CarServiceImplBase {

    private final CarRepository repository;

    public CarGrpcServiceImpl(CarRepository repository) {
        this.repository = repository;
    }

    @Override
    public void findCarById(Id request, StreamObserver<CarDto> responseObserver) {
        log.info("gRPC call received:  " + request.getId());

        tr.com.altindalorcun.carservice.dto.CarDto carDto =repository.findById(UUID.fromString(request.getId()))
                .map(tr.com.altindalorcun.carservice.dto.CarDto::new)
                .orElseThrow(() -> new CarNotFoundException("Car could not found by id : " + request.getId()));

        responseObserver.onNext(
                CarDto.newBuilder()
                        .setId(carDto.id().toString())
                        .setBrand(carDto.brand())
                        .setModel(carDto.model())
                        .setLicensePlate(carDto.licensePlate())
                        .build()
        );

        responseObserver.onCompleted();
    }
}

package ro.tuc.ds2020.services;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import ro.tuc.ds2020.dtos.MeasurementsDto;
import ro.tuc.ds2020.dtos.builders.MeasurementsBuilder;
import ro.tuc.ds2020.entities.Device;
import ro.tuc.ds2020.entities.Measurements;
import ro.tuc.ds2020.repositories.DeviceRepository;
import ro.tuc.ds2020.repositories.MeasurementsRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class MeasurementsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DeviceService.class);

    private MeasurementsRepository measurementsRepository;
    private DeviceRepository deviceRepository;

    public MeasurementsService(MeasurementsRepository measurementsRepository, DeviceRepository deviceRepository){
        this.measurementsRepository=measurementsRepository;
        this.deviceRepository=deviceRepository;
    }

    public List<MeasurementsDto> findall(){
        List<Measurements> measurements = measurementsRepository.findAll();
        return  measurements.stream().map(MeasurementsBuilder::toMeasurementsDto).collect(Collectors.toList());
    }

    public UUID insert(MeasurementsDto measurementsDto){
        Device device = deviceRepository.findByName(measurementsDto.getDeviceName());

        Measurements measurements = MeasurementsBuilder.toMeasurementsEntity(measurementsDto);

        measurements.setDevice(device);
        Measurements newMeasurements = measurementsRepository.save(measurements);
        LOGGER.debug("Measurement with id {} was inserted in db", newMeasurements.getId());
        return newMeasurements.getId();
    }

    public void delete(UUID id){
        Optional<Measurements> optionalMeasurements = measurementsRepository.findById(id);
        if(!optionalMeasurements.isPresent()){
            throw new ResourceNotFoundException(Measurements.class.getSimpleName());
        }
        measurementsRepository.delete(optionalMeasurements.get());
    }






}

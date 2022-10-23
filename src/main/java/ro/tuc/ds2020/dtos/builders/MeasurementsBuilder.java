package ro.tuc.ds2020.dtos.builders;

import ro.tuc.ds2020.dtos.MeasurementsDto;
import ro.tuc.ds2020.entities.Measurements;

public class MeasurementsBuilder {

    private MeasurementsBuilder(){

    }

    public static MeasurementsDto toMeasurementsDto(Measurements measurements){
        return new MeasurementsDto(measurements.getId(),measurements.getTimestamp(),
                measurements.getEnergyConsumption(),measurements.getDevice().getName());
    }

    public static Measurements toMeasurementsEntity (MeasurementsDto measurementsDto){

        return new Measurements(measurementsDto.getTimestamp(),measurementsDto.getEnergyConsumption());
    }

}

package ro.tuc.ds2020.dtos.builders;

import ro.tuc.ds2020.dtos.DeviceDto;
import ro.tuc.ds2020.entities.Device;

public class DeviceBuilder {

    private DeviceBuilder(){

    }

    public static DeviceDto toDeviceDto(Device device){
        return new DeviceDto(device.getId(),device.getName(),device.getDescription(),
                device.getAdress(),device.getEnergyConsumption(),
                device.getUserOfApp().getEmail());
    }

    public static Device toDeviceEntity (DeviceDto deviceDto){
        return new Device(deviceDto.getName(),deviceDto.getDescription(),
                deviceDto.getAdress(),deviceDto.getEnergyConsuption());
    }
}

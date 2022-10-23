package ro.tuc.ds2020.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import ro.tuc.ds2020.dtos.DeviceDto;
import ro.tuc.ds2020.dtos.builders.DeviceBuilder;
import ro.tuc.ds2020.entities.Device;
import ro.tuc.ds2020.entities.UserOfApp;
import ro.tuc.ds2020.repositories.DeviceRepository;
import ro.tuc.ds2020.repositories.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class DeviceService {
    private static final Logger LOGGER = LoggerFactory.getLogger(DeviceService.class);

    private DeviceRepository deviceRepository;
    private UserRepository userRepository;

    public DeviceService(DeviceRepository deviceRepository, UserRepository userRepository)
    {
        this.userRepository=userRepository;
        this.deviceRepository=deviceRepository;}

    public List<DeviceDto> findall(){

        List<Device> deviceList = deviceRepository.findAll();
        return deviceList.stream().map(DeviceBuilder::toDeviceDto).collect(Collectors.toList());

    }

    public UUID insert(DeviceDto deviceDto){

        UserOfApp userOfApp=userRepository.findByEmail(deviceDto.getUserEmail());


        Device  device= DeviceBuilder.toDeviceEntity(deviceDto);

        device.setUserOfApp(userOfApp);
        Device newDevice= deviceRepository.save(device);

        LOGGER.debug("Device with id {} was inserted in db", newDevice.getId());
        return newDevice.getId();
    }

    public void delete(UUID id){
        Optional<Device> optionalDevice = deviceRepository.findById(id);
        if(!optionalDevice.isPresent()){
            throw new ResourceNotFoundException(Device.class.getSimpleName());
        }
        deviceRepository.delete(optionalDevice.get());
    }

    public DeviceDto findById(UUID id){
        Optional<Device> optionalDevice = deviceRepository.findById(id);
        if(!optionalDevice.isPresent()){
            throw new ResourceNotFoundException(Device.class.getSimpleName());
        }
        return DeviceBuilder.toDeviceDto(optionalDevice.get());
    }




}

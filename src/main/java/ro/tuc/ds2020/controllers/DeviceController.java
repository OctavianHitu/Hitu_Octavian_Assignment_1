package ro.tuc.ds2020.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.tuc.ds2020.dtos.DeviceDto;
import ro.tuc.ds2020.entities.Device;
import ro.tuc.ds2020.services.DeviceService;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping(value = "device")
public class DeviceController {

    private final DeviceService deviceService;

    @Autowired
    public DeviceController(DeviceService deviceService){this.deviceService=deviceService;}

    @GetMapping()
    public ResponseEntity<List<DeviceDto>> getAllDevices(){
        return new ResponseEntity<>(deviceService.findall(), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<UUID> inserDevice(@RequestBody @Valid DeviceDto deviceDto){
        UUID deviceId = deviceService.insert(deviceDto);
        return new ResponseEntity<>(deviceId,HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteDevice(@PathVariable("id") UUID id){
        deviceService.delete(id);
        return new ResponseEntity<>("Deleted device with id "+ id, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getDeviceById(@PathVariable("id") UUID id){
        return new ResponseEntity<>(deviceService.findById(id),HttpStatus.OK);
    }
    @PostMapping("/update")
    public ResponseEntity<?> update(@RequestBody @Valid DeviceDto deviceDto){
        deviceService.update(deviceDto);
        return new ResponseEntity<>("Updated device"+deviceDto.getId(),HttpStatus.OK);
    }


}

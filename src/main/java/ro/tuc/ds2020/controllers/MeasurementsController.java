package ro.tuc.ds2020.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.tuc.ds2020.dtos.MeasurementsDto;
import ro.tuc.ds2020.entities.Measurements;
import ro.tuc.ds2020.services.MeasurementsService;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping(value = "meas")
public class MeasurementsController {

    private final MeasurementsService measurementsService;

    @Autowired
    public MeasurementsController(MeasurementsService measurementsService){this.measurementsService=measurementsService;}

    @GetMapping()
    public ResponseEntity<List<MeasurementsDto>> getAllMeasurements(){
        return new ResponseEntity<>(measurementsService.findall(), HttpStatus.OK);
    }


    @PostMapping()
    public ResponseEntity<UUID> insertMEasurements(@RequestBody @Valid MeasurementsDto measurementsDto){
        UUID measID= measurementsService.insert(measurementsDto);
        return new ResponseEntity<>(measID,HttpStatus.OK);
    }


    @DeleteMapping("/delete/{id}")
    public  ResponseEntity<?> deleteMeas(@PathVariable("id") UUID id){
        measurementsService.delete(id);
        return new ResponseEntity<>("Deleted measurement with id "+ id, HttpStatus.OK);

    }


}

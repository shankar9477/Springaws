package com.sbil.springdemo.controller;


import com.sbil.springdemo.exception.ResourceNotFoundException;
import com.sbil.springdemo.model.Hotel;
import com.sbil.springdemo.repository.HotelRepository;
import com.sbil.springdemo.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/hotels")
public class Hotelcontroller
{

    @Autowired
    HotelService hotelService;
    @Autowired
    HotelRepository hotelRepository;


    @PostMapping
    public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel)
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(hotelService.saveHotel(hotel));
    }

    /*
     * @GetMapping("/{hotelId}") public ResponseEntity<Hotel>
     * createHotel(@PathVariable String hotelId) { return
     * ResponseEntity.status(HttpStatus.OK).body(hotelService.getHotel(hotelId)); }
     */

    @GetMapping
    public ResponseEntity<List<Hotel>> getAll()
    {
        System.out.println("this get hotels  :");
        return ResponseEntity.ok(hotelService.getAllHotel());

    }

    @GetMapping("{id}")
    public ResponseEntity<Hotel> getHotelById(@PathVariable String id)
    {
        System.out.println("this is get by id method  :");
        return ResponseEntity.status(HttpStatus.OK).body(hotelService.getHotel(id));

    }

    @PutMapping("{id}")
    public ResponseEntity<Hotel> updateHotel(@PathVariable String id , @RequestBody Hotel hotelDetails)
    {
        System.out.println("this is hotel update method :");
        Hotel hotel =hotelRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Hotel with given id is not found on server !!"+id));
        hotel.setName(hotelDetails.getName());
        hotel.setLocation(hotelDetails.getLocation());
        hotel.setAbout(hotelDetails.getAbout());

        Hotel updateHotel =hotelRepository.save(hotel);
        return ResponseEntity.ok(updateHotel);

    }


    @DeleteMapping("{id}")
    public void deleteHotel(@PathVariable String id)
    {
        System.out.println("deleted  id :"+id);
        hotelRepository.deleteById(id);
    }


}

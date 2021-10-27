package com.northern.hotel.services.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.northern.hotel.services.data.StaffMemberRepository;
import com.northern.hotel.services.entity.StaffMember;
import com.northern.hotel.services.entity.StaffMemberModel;
import com.northern.hotel.services.exception.HotelNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/staffs")
@CrossOrigin(origins = "http://localhost:3000/")
public class StaffMemberServiceController {

    private final StaffMemberRepository repository;

    public StaffMemberServiceController(StaffMemberRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<StaffMember> getAllStaff(){
        return new ArrayList<>(this.repository.findAll());
    }

    @PostMapping
    public ResponseEntity<StaffMember> addStaffMember(@RequestBody StaffMemberModel model){
        StaffMember staffMember = this.repository.save(model.translateToStaffMember());
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(staffMember.getEmployeeId()).toUri();
        return ResponseEntity.created(location).body(staffMember);
    }

    @GetMapping("/{id}")
    public StaffMember getStaffMember(@PathVariable String id){
        Optional<StaffMember> staffMember = this.repository.findById(id);
        if(staffMember.isPresent()){
            return staffMember.get();
        }
        throw new HotelNotFoundException("StaffMember not found with id: " + id);
    }

    @PutMapping("/{id}")
    public StaffMember updateStaffMember(@PathVariable String id, @RequestBody StaffMemberModel model){
        Optional<StaffMember> existing = this.repository.findById(id);
        if(!existing.isPresent()){
            throw new HotelNotFoundException("StaffMember not found with id: " + id);
        }
        StaffMember staffMember = model.translateToStaffMember();
        staffMember.setEmployeeId(id);
        return this.repository.save(staffMember);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.RESET_CONTENT)
    public void deleteStaffMember(@PathVariable String id){
        Optional<StaffMember> existing = this.repository.findById(id);
        if(!existing.isPresent()){
            throw new HotelNotFoundException("StaffMember not found with id: " + id);
        }
        this.repository.deleteById(id);
    }
}

package com.northern.hotel.services;

import com.northern.hotel.services.data.GuestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HotelServicesApplication {

	@Autowired
	private GuestRepository guestRepository;

	public static void main(String[] args) {
		SpringApplication.run(HotelServicesApplication.class, args);
	}

}

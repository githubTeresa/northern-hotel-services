package com.northern.hotel.services.data;

import com.northern.hotel.services.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {
}

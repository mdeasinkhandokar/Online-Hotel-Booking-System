package com.example.HotelBooking.services;

import com.example.HotelBooking.dtos.Response;
import com.example.HotelBooking.dtos.RoomDTO;
import com.example.HotelBooking.enums.RoomType;
import org.jspecify.annotations.Nullable;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

public interface RoomService {


    @Nullable Response addRoom(RoomDTO roomDTO, MultipartFile multipartFile);

    @Nullable Response updateRoom(RoomDTO roomDTO, MultipartFile imageFile);

    @Nullable Response getAllRooms();

    @Nullable Response getRoomById(Long id);

    @Nullable Response deleteRoom(Long id);

    @Nullable Response getAvailableRooms(LocalDate checkInDate, LocalDate checkOutDate, RoomType roomType);

    @Nullable List<RoomType> getAllRoomTypes();

    @Nullable Response searchRoom(String input);
}

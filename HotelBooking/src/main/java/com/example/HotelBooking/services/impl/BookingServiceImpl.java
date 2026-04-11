package com.example.HotelBooking.services.impl;


import com.example.HotelBooking.dtos.BookingDTO;
import com.example.HotelBooking.dtos.NotificationDTO;
import com.example.HotelBooking.dtos.Response;
import com.example.HotelBooking.entities.Booking;
import com.example.HotelBooking.entities.Room;
import com.example.HotelBooking.entities.User;
import com.example.HotelBooking.enums.BookingStatus;
import com.example.HotelBooking.enums.PaymentStatus;
import com.example.HotelBooking.exceptions.InvalidBookingStateAndDateException;
import com.example.HotelBooking.exceptions.NotFoundException;
import com.example.HotelBooking.repositories.BookingRepository;
import com.example.HotelBooking.repositories.RoomRepository;
import com.example.HotelBooking.services.BookingService;
import com.example.HotelBooking.services.NotificationService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.data.domain.Sort;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static java.lang.Character.getType;

public class BookingServiceImpl  implements BookingService {


    private final BookingRepository bookingRepository;
    private final RoomRepository roomRepository;
    private final NotificationService notificationService;
    private final ModelMapper modelMapper;
    private final UserService userService;
    private final BookingCodeGenerator bookingCodeGenerator;


    @Override
    public Response getAllBookings() {

        List<Booking> bookingList= bookingRepository.findAll(Sort.by(Sort.Direction.DESC,"id"));
        List<BookingDTO> bookingDTOList= modelMapper.map(bookingList, new TypeToken<List<BookingDTO>>() {}.getType());

        for(BookingDTO bookingDTO : bookingDTOList){
            bookingDTO.setUser(null);
            bookingDTO.setRoom(null);

        }

        return Response.builder()
                .status(200)
                .message("success")
                .bookings(bookinDTOList)
                .build();








    }

    @Override
    public Response createBooking(BookingDTO bookingDTO) {

        User currentUser= userService.getCurrentLoggedInUser();

        Room room = roomRepository.findById(bookingDTO.getRoomId())
                .orElseThrow(()-> new NotFoundException("Room Not Found"));


        //validation: Ensure the check-in date is not before today
        if(bookingDTO.getCheckInDate().isBefore(LocalDate.now())){
            throw new InvalidBookingStateAndDateException("check in date cannot be before today");

        }
        //validation: Ensure the check-out date is not before check in date
        if(bookingDTO.getCheckInDate().isBefore(bookinDTO.getCheckInDate())){
            throw new InvalidBookingStateAndDateException("check out date cannot be before check in date");

        }
        //validation: Ensure the check-in date is not same as check out date
        if(bookingDTO.getCheckInDate().isEqual(bookingDTO.getCheckOutDate())) {
            throw new InvalidBookingStateAndDateException("check in date cannot be equal to check out date ");
        }
            //validate room availability

            boolean isAvailable= bookingRepository.isRoomAvailable(room.getId(), bookingDTO.getCheckInDate(),bookingDTO.getCheckOutDate());
            if(!isAvailable){
                throw new InvalidBookingStateAndDateException("Room is not avilable for the selected date ranges");
            }

            //calculate the total price needed to pay for the stay
            BigDecimal totalPrice = calculateTotalPrice(room, bookingDTO);
            String bookingReference = bookingCodeGenerator.generateBookingReference();


            //create and save the booking
            Booking booking= new Booking();
            booking.setUser(currentUser);
            booking.setRoom(room);
            booking.setCheckInDate(bookingDTO.getCheckInDate());
            booking.setCheckOutDate(bookingDTO.getCheckOutDate());
            booking.setTotalPrice(totalPrice);
            booking.setBookingReference(bookingReference);
            booking.setBookingStatus(BookingStatus.BOOKED);
            booking.setPaymentStatus(PaymentStatus.PENDING);
            booking.setCreatedAt(LocalDateTIme.now());

            bookingRepository.save(booking);


            //generate the payment url which will be sent via mail

            String paymentUrl= "http://localhost:3000/payment/" + bookingReference + "/" + totalPrice;

            log.info("PAYMENT LINK:{}",paymentUrl);

            //send notification via email
            NotificationDTO notificationDTO =NotificationDTO.builder()
                    .recipient(currentUser.getEmail())
                    .subject("Booking Confirmation")
                    .body(String.format("Your booking has been created successfully. Please proceed with your payment using the payment link below " +
                            "\n%s", paymentUrl))
                    .bookingReference(bookingReference)
                    .build();

            notificationService.sendEmail(notificationDTO);

            return Response.build()
                    .status(200)
                    .message("Booking is successfully")
                    .booking(bookingDTO)
                    .build();

        }



    @Override
    public Response findBookingByReferenceNo(String reference) {
        return null;
    }

    @Override
    public Response updateBooking(BookingDTO bookingDTO) {
        return null;
    }
}

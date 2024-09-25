package com.example.Book_My_Show.Services;

import com.example.Book_My_Show.Converters.TheaterConverter;
import com.example.Book_My_Show.EntryDTOs.TheaterDTO;
import com.example.Book_My_Show.Enums.SeatType;
import com.example.Book_My_Show.Modules.Theater;
import com.example.Book_My_Show.Modules.TheaterSeat;
import com.example.Book_My_Show.Repositories.TheaterRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TheaterService {
    private final TheaterRepository theaterRepository;

    public TheaterService(TheaterRepository theaterRepository)
    {
        this.theaterRepository=theaterRepository;
    }
    public String addTheater(TheaterDTO theaterDTO) throws Exception
    {
        if(theaterDTO.getTheaterLocation()==null || theaterDTO.getTheaterName()==null)
        {
           throw new Exception("Name & Location Should Be Valid");
        }
        Theater theater = TheaterConverter.covertEntryDtoToEntity(theaterDTO);
        List<TheaterSeat> theaterSeats = createTheaterSeats(theaterDTO, theater);
        theater.setTheaterSeats(theaterSeats);
        theaterRepository.save(theater);
        return "Theater added Successfully";
    }

    public List<TheaterSeat> createTheaterSeats(TheaterDTO theaterDTO , Theater theater)
    {
        List<TheaterSeat> theaterSeatsList = new ArrayList<>();
        int noOfClassicSeats = theaterDTO.getClassicSeatCount();
        int noOfPremiumSeats = theaterDTO.getPremiumSeatCount();
        TheaterSeat theaterSeat = new TheaterSeat();
        //creating the classic seats
        for(int i =1;i<=noOfClassicSeats;i++)
        {
            theaterSeat = TheaterSeat.builder().seatType(SeatType.CLASSIC).seatNo(i+"C").build();
            theaterSeatsList.add(theaterSeat);
            theaterSeat.setTheater(theater);
        }


        //create the premium seats
        for(int i =1;i<=noOfPremiumSeats;i++)
        {
            theaterSeat = TheaterSeat.builder().seatType(SeatType.PREMIUM).seatNo(i+"P").build();
            theaterSeatsList.add(theaterSeat);
            theaterSeat.setTheater(theater);
        }

        return theaterSeatsList;
    }
}

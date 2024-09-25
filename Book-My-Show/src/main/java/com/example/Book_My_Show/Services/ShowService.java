package com.example.Book_My_Show.Services;

import com.example.Book_My_Show.Converters.ShowConverters;
import com.example.Book_My_Show.EntryDTOs.ShowDTO;
import com.example.Book_My_Show.Enums.SeatType;
import com.example.Book_My_Show.Modules.*;
import com.example.Book_My_Show.Repositories.MovieRepository;
import com.example.Book_My_Show.Repositories.ShowRepository;
import com.example.Book_My_Show.Repositories.TheaterRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ShowService {
    private final ShowRepository showRepository;
    private final TheaterRepository theaterRepository;
    private final MovieRepository movieRepository;

    public ShowService(ShowRepository showRepository , TheaterRepository theaterRepository ,MovieRepository movieRepository)
    {
        this.showRepository=showRepository;
        this.theaterRepository=theaterRepository;
        this.movieRepository=movieRepository;
    }

    public String addShow(ShowDTO showDTO) {
        Show show = ShowConverters.covertEntryDtoToEntity(showDTO);
        int movieId = showDTO.getMovieId();
        int theaterId = showDTO.getTheaterId();
        Movie movie = movieRepository.findById(movieId).get();
        Theater theater = theaterRepository.findById(theaterId).get();
        show.setShowName(movie.getMovieName());
        //setting the attributes of foreign key
        show.setMovie(movie);
        show.setTheater(theater);
        List<ShowSeat> showSeats = createShowSeat(showDTO,show);
        show.setShowSeats(showSeats);
        //now saving and updating the entities.
        showRepository.save(show);
        //now we need to update the parent entities.
        movie.getShowList().add(show);
        theater.getShowList().add(show);
        movieRepository.save(movie);
        theaterRepository.save(theater);
        return "Show added Successfully";
    }

    public List<ShowSeat> createShowSeat(ShowDTO showDTO , Show show)
    {
        Theater theater = show.getTheater();
        List<TheaterSeat> theaterSeatList = theater.getTheaterSeats();
        List<ShowSeat> showSeats = new ArrayList<>();
        for(TheaterSeat theaterSeat : theaterSeatList) {
            ShowSeat showSeat = new ShowSeat();
            showSeat.setSeatNo(theaterSeat.getSeatNo());
            showSeat.setSeatType(theaterSeat.getSeatType());
            if (theaterSeat.getSeatType().equals(SeatType.CLASSIC)) {
                showSeat.setSeatPrice(showDTO.getClassicSeatsPrice());
            } else {
                showSeat.setSeatPrice(showDTO.getPremiumSeatsPrice());
            }
            showSeat.setSeatStatus(false);
            showSeat.setShow(show);
            showSeats.add(showSeat);
        }
        return showSeats;
    }
    public String deleteShow(int showId) {
        Optional<Show> show = showRepository.findById(showId);
        if(show.isPresent())
        {
            showRepository.delete(show.get());
            return "Show deleted successfully";
        }
        else {
            return "Show is not present";
        }
    }
}

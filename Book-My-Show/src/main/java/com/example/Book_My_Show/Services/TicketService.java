package com.example.Book_My_Show.Services;

import com.example.Book_My_Show.Converters.TicketConverter;
import com.example.Book_My_Show.EntryDTOs.TicketDTO;
import com.example.Book_My_Show.Modules.Show;
import com.example.Book_My_Show.Modules.ShowSeat;
import com.example.Book_My_Show.Modules.Ticket;
import com.example.Book_My_Show.Modules.User;
import com.example.Book_My_Show.Repositories.ShowRepository;
import com.example.Book_My_Show.Repositories.TicketRepository;
import com.example.Book_My_Show.Repositories.UserRepository;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TicketService {
    private  final TicketRepository ticketRepository;
    private  final ShowRepository showRepository;
    private  final UserRepository userRepository;
    private  final JavaMailSender javaMailSender;

    public TicketService(TicketRepository ticketRepository , ShowRepository showRepository,
                         UserRepository userRepository, JavaMailSender javaMailSender)
    {
        this.ticketRepository=ticketRepository;
        this.showRepository= showRepository;
        this.userRepository = userRepository;
        this.javaMailSender = javaMailSender;
    }
    public String addTicket(TicketDTO ticketDTO) throws Exception {
        Ticket ticket = TicketConverter.covertEntryDtoToEntity(ticketDTO);
        //validation - check req seat are available or not
        boolean isValidRequest = checkValidityOfReqSeats(ticketDTO);
        if(!isValidRequest)
        {
            throw new Exception("Requested seats are not available");
        }
        Show show = showRepository.findById(ticketDTO.getShowId()).get();
        //calculate the total amount
        int amount = totalAmountOfSeats(ticketDTO);

        ticket.setTotalAmount(amount);
        ticket.setMovieName(show.getMovie().getMovieName());
        ticket.setShowDate(show.getShowDate());
        ticket.setShowTime(show.getShowTime());
        ticket.setTheaterName(show.getTheater().getTheaterName());
        //we need to set the that talk about req seats
        List<String> reqSeat = ticketDTO.getRequestedSeat();
        String allottedSeats = getAllottedSeatsFromShowSeat(reqSeat);
        ticket.setBookedSeats(allottedSeats);

        User user1 = userRepository.findById(ticketDTO.getUserId()).get();
        ticket.setUser(user1);
        ticket.setShow(show);
        ticketRepository.save(ticket);
        List<Ticket> ticketList = show.getTicketList();
        ticketList.add(ticket);
        show.setTicketList(ticketList);
        showRepository.save(show);
        List<Ticket> ticketList1 = user1.getTicketList();
        ticketList1.add(ticket);
        user1.setTicketList(ticketList1);
        userRepository.save(user1);
        String body = "Hey ! this is to confirm your booking for seat number " + allottedSeats + "for the movie" + " " + ticket.getMovieName() +" " + " is done";
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,true);
        mimeMessageHelper.setFrom("jainbhavya8718@gmail.com");
        mimeMessageHelper.setTo(user1.getUserEmail());
        mimeMessageHelper.setSubject("Confirm Your Booked Ticket");
        mimeMessageHelper.setText(body);
        javaMailSender.send(mimeMessage);
        return "Ticket Added successfully";
    }

    private String getAllottedSeatsFromShowSeat(List<String> reqSeat) {
        StringBuilder answer = new StringBuilder();
        for(String seat : reqSeat)
        {
            answer.append(seat).append(",");
        }
        return answer.toString();
    }

    private int totalAmountOfSeats(TicketDTO ticketDTO) {
        int showId = ticketDTO.getShowId();
        int totalAmount = 0;
        List<String> reqSeat = ticketDTO.getRequestedSeat();
        Show show = showRepository.findById(showId).get();
        List<ShowSeat> showSeatsList = show.getShowSeats();
        for(ShowSeat showSeat : showSeatsList)
        {
            String seatNo = showSeat.getSeatNo();
            if(reqSeat.contains(seatNo))
            {
                totalAmount += showSeat.getSeatPrice();
                showSeat.setSeatStatus(true);
            }
        }
      return  totalAmount;
    }

    private boolean checkValidityOfReqSeats(TicketDTO ticketDTO) {
        int showId = ticketDTO.getShowId();
        List<String> reqSeat = ticketDTO.getRequestedSeat();
        Show show = showRepository.findById(showId).get();
        List<ShowSeat> showSeatsList = show.getShowSeats();
        for(ShowSeat showSeat : showSeatsList)
        {
            String seatNo = showSeat.getSeatNo();
            if(reqSeat.contains(seatNo))
                {
                    if(showSeat.isSeatStatus()){
                        return false;
                    }
                }
        }
        return true;
    }

    public String deleteTicket(int ticketId) {
        Optional<Ticket> ticket = ticketRepository.findById(ticketId);
        if(ticket.isPresent())
        {
            ticketRepository.delete(ticket.get());
            return "Ticket removed";
        }
        else {
            return "There is no ticket with this Id";
        }
    }

}

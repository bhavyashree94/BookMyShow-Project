package com.example.Book_My_Show.Services;

import com.example.Book_My_Show.Converters.UserConverter;
import com.example.Book_My_Show.EntryDTOs.UserDTO;
import com.example.Book_My_Show.Modules.User;
import com.example.Book_My_Show.Repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository)
    {
        this.userRepository=userRepository;
    }
    public String addUser(UserDTO userDTO) {
        User user = UserConverter.covertEntryDtoToEntity(userDTO);
        userRepository.save(user);
        return "user Added Successfully";
    }

    public String deleteUser(int userId) {
        Optional<User> user = userRepository.findById(userId);
        if(user.isPresent())
        {
            userRepository.delete(user.get());
            return "User deleted Successfully";
        }
        else {
            return "User Not Found";
        }
    }
}

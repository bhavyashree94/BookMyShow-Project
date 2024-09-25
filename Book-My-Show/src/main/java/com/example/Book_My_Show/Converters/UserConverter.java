package com.example.Book_My_Show.Converters;

import com.example.Book_My_Show.EntryDTOs.MovieEntryDTO;
import com.example.Book_My_Show.EntryDTOs.UserDTO;
import com.example.Book_My_Show.Modules.Movie;
import com.example.Book_My_Show.Modules.User;

public class UserConverter {
    public static User covertEntryDtoToEntity(UserDTO userDTO)
    {
        User user = User.builder().userName(userDTO.getUserName())
                .userMobile(userDTO.getUserMobile())
                .userEmail(userDTO.getUserEmail())
                .age(userDTO.getAge())
                .address(userDTO.getAddress()).build();
        return user;
    }
}

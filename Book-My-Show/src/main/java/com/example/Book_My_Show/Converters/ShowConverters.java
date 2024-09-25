package com.example.Book_My_Show.Converters;

import com.example.Book_My_Show.EntryDTOs.ShowDTO;
import com.example.Book_My_Show.Modules.Show;

public class ShowConverters {
    public static Show covertEntryDtoToEntity(ShowDTO showDTO)
    {
        Show show = Show.builder()
                .showDate(showDTO.getLocalDate())
                .showType(showDTO.getShowType())
                .showTime(showDTO.getLocalTime())
                .build();
        return show;
    }
}

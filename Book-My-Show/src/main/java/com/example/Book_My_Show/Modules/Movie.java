package com.example.Book_My_Show.Modules;

import com.example.Book_My_Show.Enums.Genre;
import com.example.Book_My_Show.Enums.MovieLanguage;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="movie_info")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int movieId;

    private String movieName;

    private double ratings;

    private int movieDuration;

    @Enumerated(value = EnumType.STRING)
    Genre genre;

    @Enumerated(value = EnumType.STRING)
    MovieLanguage movieLanguage;

    @OneToMany(mappedBy = "movie",cascade = CascadeType.ALL)
    List<Show>showList;
}

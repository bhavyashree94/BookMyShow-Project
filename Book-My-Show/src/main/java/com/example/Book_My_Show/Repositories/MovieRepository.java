package com.example.Book_My_Show.Repositories;

import com.example.Book_My_Show.Modules.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie,Integer> {
}

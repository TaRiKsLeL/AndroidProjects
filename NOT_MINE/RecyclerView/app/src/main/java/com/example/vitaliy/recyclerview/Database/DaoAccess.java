package com.example.vitaliy.recyclerview.Database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface DaoAccess {

    @Insert
    void insertSingleMovie (Movies movies);
    @Insert
    void insertMultipleMovies (List<Movies> moviesList);
    @Query("SELECT * FROM Movies WHERE movieId = :movieId")
    Movies fetchOneMoviesbyMovieId (int movieId);
    @Query("SELECT * FROM Movies")
    List <Movies> fetchAllMovies();
    @Update
    void updateMovie (Movies movies);
    @Delete
    void deleteMovie (Movies movies);
}
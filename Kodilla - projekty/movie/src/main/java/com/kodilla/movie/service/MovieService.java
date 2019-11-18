package com.kodilla.movie.service;

import com.kodilla.movie.controller.MovieNotFound;
import com.kodilla.movie.domain.Movie;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class MovieService {
    private Set<Movie> movies;

    private MovieService() {
        this.movies = exampleData();
    }

    private Set<Movie> exampleData() {
        Set<Movie> movies = new HashSet<>();
        movies.add(new Movie(1L,"Cracking the Coding Interview: 189 Programming Questions and Solutions", "Gayle Laakmann McDowell"));
        movies.add(new Movie(2L,"Introduction to Algorithms", "The MIT Press"));
        movies.add(new Movie(3L,"Introduction to the Theory of Computation", "Michael Sipser"));
        movies.add(new Movie(4L,"Operating System Concepts", "Silberschatz, Galvin, Gagne"));
        movies.add(new Movie(5L,"The Silent Patient", "Alex Michaelides"));
        movies.add(new Movie(6L,"The Handmaid's Tale", "Margaret Atwood"));
        movies.add(new Movie(7L,"Watch Us Rise", "Renée Watson,  Ellen Hagan"));
        return movies;
    }

    public List<Movie> findAllMovies() {
        return movies.stream().collect(Collectors.toList());
    }


    public Optional<Movie> findMovieById(Long movieId) {
        return movies.stream()
                .filter(movie -> movie.equals(movieId))
                .findFirst();
    }

    public void createMovie(Movie movie) {
        movies.add(movie);
    }


    public void deleteMovie(Long movieId) throws MovieNotFound{
        movies.remove(findMovieById(movieId).orElseThrow(MovieNotFound::new));
    }

    public Movie updateMovie(Long movieId, Movie movie) {
        Movie movie1 = findMovieById(movieId).get();
        movie1.setId(movie.getId());
        movie1.setTitle(movie.getTitle());
        movie1.setAuthor(movie.getAuthor());
        return movie1;
    }
}

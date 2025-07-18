package com.example.ss9.controller;


import com.example.ss9.Service.CloudinaryService;
import com.example.ss9.Service.LogAnalysisService;
import com.example.ss9.Service.MovieService;
import com.example.ss9.model.DTO.MovieDTO;
import com.example.ss9.model.entity.Movie;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/movies")
@Slf4j
public class MovieController {

    @Autowired
    private MovieService movieService;

    @Autowired
    private CloudinaryService cloudinaryService;

    @Autowired
    private LogAnalysisService logAnalysisService;

    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_BLUE = "\u001B[34m";

    @PostMapping
    public ResponseEntity<Movie> addMovie(@ModelAttribute MovieDTO movieDTO) {
        try {
            Movie movie = new Movie();
            movie.setTitle(movieDTO.getTitle());
            movie.setDescription(movieDTO.getDescription());
            movie.setReleaseDate(movieDTO.getReleaseDate());

            if (movieDTO.getPosterFile() != null && !movieDTO.getPosterFile().isEmpty()) {
                String posterUrl = cloudinaryService.uploadFile(movieDTO.getPosterFile());
                movie.setPoster(posterUrl);
            }

            Movie savedMovie = movieService.saveMovie(movie);
            log.info("Movie added successfully: {} at {}",
                    savedMovie.getTitle(), LocalDateTime.now());
            return ResponseEntity.ok(savedMovie);
        } catch (Exception e) {
            log.error("Error adding movie: {}", e.getMessage(), e);
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Movie> updateMovie(@PathVariable Long id, @ModelAttribute MovieDTO movieDTO) {
        try {
            Optional<Movie> oldMovieOpt = movieService.getMovieById(id);
            if (oldMovieOpt.isEmpty()) {
                log.error(ANSI_RED + "Movie not found with id: {}" + ANSI_RESET, id);
                return ResponseEntity.notFound().build();
            }

            Movie oldMovie = oldMovieOpt.get();
            Movie updatedMovie = new Movie();
            updatedMovie.setId(id);
            updatedMovie.setTitle(movieDTO.getTitle());
            updatedMovie.setDescription(movieDTO.getDescription());
            updatedMovie.setReleaseDate(movieDTO.getReleaseDate());

            if (movieDTO.getPosterFile() != null && !movieDTO.getPosterFile().isEmpty()) {
                String posterUrl = cloudinaryService.uploadFile(movieDTO.getPosterFile());
                updatedMovie.setPoster(posterUrl);
            } else {
                updatedMovie.setPoster(oldMovie.getPoster());
            }

            Movie result = movieService.updateMovie(id, updatedMovie);

            if (result != null) {
                log.info("Movie updated successfully:\n" +
                                ANSI_YELLOW + "Old info: {}" + ANSI_RESET + "\n" +
                                ANSI_BLUE + "New info: {}" + ANSI_RESET,
                        oldMovie.toString(), result.toString());
                return ResponseEntity.ok(result);
            } else {
                log.error(ANSI_RED + "Failed to update movie with id: {}" + ANSI_RESET, id);
                return ResponseEntity.badRequest().build();
            }
        } catch (Exception e) {
            log.error(ANSI_RED + "Error updating movie: {}" + ANSI_RESET, e.getMessage(), e);
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable Long id) {
        try {
            Optional<Movie> movieOpt = movieService.getMovieById(id);
            if (movieOpt.isEmpty()) {
                log.error("Movie not found with id: {}", id);
                return ResponseEntity.notFound().build();
            }

            Movie movie = movieOpt.get();
            boolean deleted = movieService.deleteMovie(id);

            if (deleted) {
                log.info(ANSI_RED + "xóa thành công" + ANSI_RESET + " " +
                        ANSI_BLUE + "{}" + ANSI_RESET, movie.toString());
                return ResponseEntity.ok().build();
            } else {
                log.error("Failed to delete movie with id: {}", id);
                return ResponseEntity.badRequest().build();
            }
        } catch (Exception e) {
            log.error("Error deleting movie: {}", e.getMessage(), e);
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Movie>> getMovies(@RequestParam(required = false) String searchMovie) {
        try {
            long startTime = System.currentTimeMillis();
            List<Movie> movies;

            if (searchMovie != null && !searchMovie.trim().isEmpty()) {
                movies = movieService.searchMovies(searchMovie);
                log.info(ANSI_BLUE + "Search keyword: {}, Results count: {}, Execution time: {} ms" + ANSI_RESET,
                        searchMovie, movies.size(), System.currentTimeMillis() - startTime);
            } else {
                movies = movieService.getAllMovies();
                log.info(ANSI_BLUE + "Get all movies, Results count: {}, Execution time: {} ms" + ANSI_RESET,
                        movies.size(), System.currentTimeMillis() - startTime);
            }

            return ResponseEntity.ok(movies);
        } catch (Exception e) {
            log.error("Error getting movies: {}", e.getMessage(), e);
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/search-logs")
    public ResponseEntity<Map<String, Integer>> getSearchLogs() {
        try {
            Map<String, Integer> searchKeywords = logAnalysisService.getSearchKeywords();
            log.info("Retrieved search keywords from logs: {} unique keywords", searchKeywords.size());
            return ResponseEntity.ok(searchKeywords);
        } catch (Exception e) {
            log.error("Error getting search logs: {}", e.getMessage(), e);
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/suggestions")
    public ResponseEntity<List<Movie>> getMovieSuggestions() {
        try {
            List<Movie> suggestions = logAnalysisService.getMovieSuggestions();
            log.info("Retrieved movie suggestions: {} movies", suggestions.size());
            return ResponseEntity.ok(suggestions);
        } catch (Exception e) {
            log.error("Error getting movie suggestions: {}", e.getMessage(), e);
            return ResponseEntity.badRequest().build();
        }
    }
}

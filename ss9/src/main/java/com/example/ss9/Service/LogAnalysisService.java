package com.example.ss9.Service;

import com.example.ss9.Service.MovieService;
import com.example.ss9.model.entity.Movie;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
@Slf4j
public class LogAnalysisService {

    @Autowired
    private MovieService movieService;

    private static final String LOG_FILE_PATH = "logs/app.log";

    public Map<String, Integer> getSearchKeywords() {
        Map<String, Integer> searchKeywords = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(LOG_FILE_PATH))) {
            String line;
            Pattern searchPattern = Pattern.compile("Search keyword: (.*?), Results count:");

            while ((line = reader.readLine()) != null) {
                Matcher matcher = searchPattern.matcher(line);
                if (matcher.find()) {
                    String keyword = matcher.group(1).trim();
                    if (!keyword.isEmpty()) {
                        searchKeywords.put(keyword, searchKeywords.getOrDefault(keyword, 0) + 1);
                    }
                }
            }
        } catch (IOException e) {
            log.error("Error reading log file: {}", e.getMessage(), e);
        }

        return searchKeywords;
    }


    public List<Movie> getMovieSuggestions() {
        Map<String, Integer> searchKeywords = getSearchKeywords();
        Set<Movie> suggestedMovies = new HashSet<>();

        List<String> topKeywords = searchKeywords.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .limit(10)
                .map(Map.Entry::getKey)
                .toList();

        for (String keyword : topKeywords) {
            try {
                List<Movie> movies = movieService.searchMovies(keyword);
                suggestedMovies.addAll(movies);
            } catch (Exception e) {
                log.error("Error searching movies for keyword '{}': {}", keyword, e.getMessage());
            }
        }

        log.info("Generated {} movie suggestions based on search logs", suggestedMovies.size());
        return new ArrayList<>(suggestedMovies);
    }
}

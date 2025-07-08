package com.example.ss2.controller;

import com.example.ss2.entity.Movie;
import com.example.ss2.entity.ScreenRoom;
import com.example.ss2.entity.Showtime;
import com.example.ss2.service.MovieService;
import com.example.ss2.service.ScreenRoomService;
import com.example.ss2.service.ShowtimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/showtimes")
public class ShowtimeController {

    @Autowired
    private ShowtimeService showtimeService;
    @Autowired
    private MovieService movieService;
    @Autowired
    private ScreenRoomService screenRoomService;

    @GetMapping
    public String listShowtimes(
            @RequestParam(required = false) Long movieId,
            @RequestParam(required = false) Long screenRoomId,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startTime,
            Model model) {

        List<Showtime> showtimes = showtimeService.filterShowtimes(movieId, screenRoomId, startTime);

        model.addAttribute("showtimes", showtimes);
        model.addAttribute("movies", movieService.findAll());
        model.addAttribute("screenRooms", screenRoomService.findAll());
        model.addAttribute("selectedMovieId", movieId);
        model.addAttribute("selectedScreenRoomId", screenRoomId);
        model.addAttribute("selectedStartTime", startTime);
        return "showtime-list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("showtime", new Showtime());
        model.addAttribute("movies", movieService.findAll());
        model.addAttribute("screenRooms", screenRoomService.findAll());
        model.addAttribute("formTitle", "Thêm lịch chiếu mới");
        return "showtime-form";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Optional<Showtime> showtimeOpt = showtimeService.findById(id);
        if (showtimeOpt.isPresent()) {
            Showtime showtime = showtimeOpt.get();
            if (showtime.getMovie() != null) showtime.setMovieId(showtime.getMovie().getId());
            if (showtime.getScreenRoom() != null) showtime.setScreenRoomId(showtime.getScreenRoom().getId());
            model.addAttribute("showtime", showtime);
            model.addAttribute("movies", movieService.findAll());
            model.addAttribute("screenRooms", screenRoomService.findAll());
            model.addAttribute("formTitle", "Sửa lịch chiếu");
            return "showtime-form";
        }
        return "redirect:/showtimes";
    }

    @PostMapping({"/add", "/edit/{id}"})
    public String saveOrUpdateShowtime(@ModelAttribute Showtime showtime,
                                       @PathVariable(required = false) Long id,
                                       Model model) {
        if (id != null) {
            showtime.setId(id);
        }
        if (showtime.getMovieId() == null || showtime.getScreenRoomId() == null
                || showtime.getStartTime() == null || showtime.getEndTime() == null) {
            model.addAttribute("error", "Vui lòng nhập đầy đủ thông tin bắt buộc!");
            model.addAttribute("showtime", showtime);
            model.addAttribute("movies", movieService.findAll());
            model.addAttribute("screenRooms", screenRoomService.findAll());
            model.addAttribute("formTitle", id == null ? "Thêm lịch chiếu mới" : "Sửa lịch chiếu");
            return "showtime-form";
        }
        Movie movie = movieService.findById(showtime.getMovieId()).orElse(null);
        ScreenRoom screenRoom = screenRoomService.findById(showtime.getScreenRoomId()).orElse(null);
        showtime.setMovie(movie);
        showtime.setScreenRoom(screenRoom);
        showtimeService.save(showtime);
        return "redirect:/showtimes";
    }

    @PostMapping("/delete/{id}")
    public String deleteShowtime(@PathVariable Long id) {
        showtimeService.delete(id);
        return "redirect:/showtimes";
    }
}

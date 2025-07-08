    package com.example.ss2.entity;

    import jakarta.persistence.*;
    import lombok.AllArgsConstructor;
    import lombok.Getter;
    import lombok.NoArgsConstructor;
    import lombok.Setter;

    import java.time.LocalDateTime;

    @Entity
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    public class Showtime {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @ManyToOne
        @JoinColumn(name = "movie_id")
        private Movie movie;

        @ManyToOne
        @JoinColumn(name = "screen_room_id")
        private ScreenRoom screenRoom;

        private LocalDateTime startTime;
        private LocalDateTime endTime;
        private int numberSeatEmpty;

        @Transient
        private Long movieId;

        @Transient
        private Long screenRoomId;

    }

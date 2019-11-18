package com.kodilla.rating.domain;

public class Rating {
    private Long id;
    private Long movieId;
    private Integer stars;

    public Rating(Long id, Long movieId, Integer stars) {
        this.id = id;
        this.movieId = movieId;
        this.stars = stars;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    public Integer getStars() {
        return stars;
    }

    public void setStars(Integer stars) {
        this.stars = stars;
    }
}

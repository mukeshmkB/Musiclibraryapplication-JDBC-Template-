package com.example.song.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.ArrayList;
import com.example.song.service.SongH2Service;
import com.example.song.model.Song;

/**
 * Controller class for handling HTTP requests related to songs.
 */
@RestController
public class SongController {

    @Autowired
    public SongH2Service songService;

    /**
     * Retrieves all songs.
     * 
     * @return A list of all songs.
     */
    @GetMapping("/songs")
    public ArrayList<Song> getAllSongs() {
        return songService.getAllSongs();
    }

    /**
     * Retrieves a song by its ID.
     * 
     * @param songId The ID of the song to retrieve.
     * @return The retrieved song.
     */
    @GetMapping("/songs/{songId}")
    public Song getSongById(@PathVariable("songId") int songId) {
        return songService.getSongById(songId);
    }

    /**
     * Adds a new song.
     * 
     * @param song The song object to be added.
     * @return The added song.
     */
    @PostMapping("/songs")
    public Song addSong(@RequestBody Song song) {
        return songService.addSong(song);
    }

    /**
     * Updates song information.
     * 
     * @param songId The ID of the song to be updated.
     * @param song   The updated song object.
     * @return The updated song.
     */
    @PutMapping("/songs/{songId}")
    public Song updateSong(@PathVariable("songId") int songId, @RequestBody Song song) {
        return songService.updateSong(songId, song);
    }

    /**
     * Deletes a song by its ID.
     * 
     * @param songId The ID of the song to be deleted.
     */
    @DeleteMapping("/songs/{songId}")
    public void deleteSong(@PathVariable("songId") int songId) {
        songService.deleteSong(songId);
    }

}
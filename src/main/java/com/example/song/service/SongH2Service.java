package com.example.song.service;

import com.example.song.model.SongRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;

import com.example.song.model.Song;
import com.example.song.repository.SongRepository;

/**
 * Service implementation for managing song operations with an H2 database.
 */
@Service
public class SongH2Service implements SongRepository {

    @Autowired
    private JdbcTemplate db;

    /**
     * Deletes a song by its ID.
     * 
     * @param songId The ID of the song to delete.
     */
    @Override
    public void deleteSong(int songId) {
        db.update("delete from playlist where songId = ?", songId);
    }

    /**
     * Updates song information.
     * 
     * @param songId The ID of the song to update.
     * @param song   The updated song object.
     * @return The updated song.
     */
    @Override
    public Song updateSong(int songId, Song song) {
        // Update song name if provided
        if (song.getSongName() != null) {
            db.update("update playlist set songName = ? where songId = ?", song.getSongName(), songId);
        }
        // Update lyricist if provided
        if (song.getLyricist() != null) {
            db.update("update playlist set lyricist = ? where songId = ?", song.getLyricist(), songId);
        }
        // Update singer if provided
        if (song.getSinger() != null) {
            db.update("update playlist set singer = ? where songId = ?", song.getSinger(), songId);
        }
        // Update music director if provided
        if (song.getMusicDirector() != null) {
            db.update("update playlist set musicDirector = ? where songId = ?", song.getMusicDirector(), songId);
        }

        try {
            // Attempt to retrieve and return the updated song
            return getSongById(songId);
        } catch (Exception e) {
            // If song not found, throw 404 status exception
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Adds a new song.
     * 
     * @param song The song object to add.
     * @return The added song.
     */
    @Override
    public Song addSong(Song song) {
        db.update("insert into playlist (songName, lyricist, singer, musicDirector) values (?, ?, ?, ?)",
                song.getSongName(), song.getLyricist(), song.getSinger(), song.getMusicDirector());

        // Retrieve and return the added song
        Song savedSong = db.queryForObject(
                "select * from playlist where songName = ? and lyricist = ? and singer = ? and musicDirector = ?",
                new SongRowMapper(), song.getSongName(), song.getLyricist(), song.getSinger(),
                song.getMusicDirector());
        return savedSong;
    }

    /**
     * Retrieves a song by its ID.
     * 
     * @param songId The ID of the song to retrieve.
     * @return The retrieved song.
     */
    @Override
    public Song getSongById(int songId) {
        try {
            // Retrieve song from database by ID
            Song song = db.queryForObject("select * from playlist where songId = ?", new SongRowMapper(), songId);
            return song;
        } catch (Exception e) {
            // If song not found, throw 404 status exception
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Retrieves all songs.
     * 
     * @return A list of all songs.
     */
    @Override
    public ArrayList<Song> getAllSongs() {
        // Retrieve all songs from database
        return (ArrayList<Song>) db.query("select * from playlist", new SongRowMapper());
    }

}
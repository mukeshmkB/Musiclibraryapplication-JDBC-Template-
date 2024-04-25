package com.example.song.repository;

import java.util.ArrayList;
import com.example.song.model.Song;

/**
 * Repository interface for managing song data.
 */
public interface SongRepository {

    /**
     * Retrieves all songs.
     * 
     * @return A list of all songs.
     */
    ArrayList<Song> getAllSongs();

    /**
     * Retrieves a song by its ID.
     * 
     * @param songId The ID of the song to retrieve.
     * @return The retrieved song.
     */
    Song getSongById(int songId);

    /**
     * Adds a new song.
     * 
     * @param song The song object to be added.
     * @return The added song.
     */
    Song addSong(Song song);

    /**
     * Updates song information.
     * 
     * @param songId The ID of the song to be updated.
     * @param song   The updated song object.
     * @return The updated song.
     */
    Song updateSong(int songId, Song song);

    /**
     * Deletes a song by its ID.
     * 
     * @param songId The ID of the song to be deleted.
     */
    void deleteSong(int songId);
}
package com.example.song.model;

import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * RowMapper implementation for mapping ResultSet rows to Song objects.
 */
public class SongRowMapper implements RowMapper<Song> {

    /**
     * Maps a row of the ResultSet to a Song object.
     * 
     * @param rs     The ResultSet containing the data.
     * @param rowNum The current row number.
     * @return A Song object mapped from the ResultSet row.
     * @throws SQLException If a SQL exception occurs.
     */
     
    @Override
    public Song mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Song(
                rs.getInt("songId"),
                rs.getString("songName"),
                rs.getString("lyricist"),
                rs.getString("singer"),
                rs.getString("musicDirector"));
    }
}
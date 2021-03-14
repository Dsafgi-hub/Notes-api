package com.example.api.dao;

import com.example.api.model.Note;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Repository
public class NoteJdbc {
    private final JdbcTemplate jdbcTemplate;

    public NoteJdbc(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Note mapNote(ResultSet rs, int i) throws SQLException {
        return new Note(
                rs.getInt("id"),
                rs.getString("title"),
                rs.getString("content")
        );
    }
     public ArrayList<Note> getNotes() {
         return (ArrayList<Note>) jdbcTemplate.query("SELECT * FROM notes", this::mapNote);
     }

     public Note getNote(int id) {
         return jdbcTemplate.queryForObject("SELECT * FROM notes WHERE id = ?", this::mapNote, id);
     }

     public void deleteNote(int id) {
         jdbcTemplate.update("DELETE FROM notes WHERE id = ?", id);
     }

    public Note insertNote(int id, String title, String content) {
        jdbcTemplate.update("INSERT INTO notes(id, title, content) VALUES(?, ?, ?)", id, title, content);
        return jdbcTemplate.queryForObject("SELECT * FROM notes WHERE id = ?", this::mapNote, id);
    }

    public void updateNote(int id, String title, String content) {
        jdbcTemplate.update("UPDATE notes SET title = ?, content = ? where id = ?", title, content, id);
    }


}

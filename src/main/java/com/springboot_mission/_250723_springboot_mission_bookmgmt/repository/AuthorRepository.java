package com.springboot_mission._250723_springboot_mission_bookmgmt.repository;

import com.springboot_mission._250723_springboot_mission_bookmgmt.model.Author;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class AuthorRepository{
    private final JdbcTemplate jdbcTemplate;

    private final RowMapper<Author> mapper = new RowMapper<>() {
        @Override
        public Author mapRow(ResultSet rs, int rowNum) throws SQLException {
            Author author = new Author();
            author.setId(rs.getLong("id"));
            author.setName(rs.getString("name"));
            return author;
        }
    };

    public List<Author> finalAll() {
        return jdbcTemplate.query("SELECT * FROM authors", mapper);
    }

    public Author findById(Long id) {
        return jdbcTemplate.queryForObject(
                "SELECT * FROM authors WHERE id = ?", mapper, id
        );
    }

    public void save(Author author) {
        jdbcTemplate.update(
                "INSERT INTO authors (name) VALUES (?)", author.getName()
        );
    }
    public void update(Author author) {
        jdbcTemplate.update(
                "UPDATE authors SET name = ? WHERE id = ?",
                author.getName(), author.getId()
        );
    }
    public void delete(Long id) {
        jdbcTemplate.update("DELETE FROM authors WHERE id = ?", id);
    }
}

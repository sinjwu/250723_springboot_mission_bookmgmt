package com.springboot_mission._250723_springboot_mission_bookmgmt.repository;

import com.springboot_mission._250723_springboot_mission_bookmgmt.model.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class BookRepository {
    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<Book> mapper = new RowMapper<>() {
        @Override
        public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
            Book book = new Book();
            book.setId(rs.getLong("id"));
            book.setTitle(rs.getString("title"));
            book.setAuthorId(rs.getLong("author_id"));
            book.setAuthorName(rs.getString("author_name"));
            book.setPublishedDate(rs.getDate("published_date").toLocalDate());
            return book;
        }
    };
    public List<Book> findAll() {
        return jdbcTemplate.query(
                "SELECT b.*, a.name AS author_name FROM books b JOIN authors a ON b.author_id = a.id",
                mapper
        );
    }
    public Book findById(Long id) {
        return jdbcTemplate.queryForObject(
                "SELECT b.*, a.name AS author_name FROM books b JOIN authors a ON b.author_id = a.id WHERE b.id = ?",
                mapper, id
        );
    }
    public void save(Book book) {
        jdbcTemplate.update(
                "INSERT INTO books(title, author_id, published_date) VALUES(?,?,?)",
                book.getTitle(), book.getAuthorId(), book.getPublishedDate()
        );
    }
    public void update(Book book) {
        jdbcTemplate.update(
                "UPDATE books SET title=?, author_id=?, published_date=? WHERE id=?",
                book.getTitle(), book.getAuthorId(), book.getPublishedDate(), book.getId()
        );
    }
    public void delete(Long id) {
        jdbcTemplate.update("DELETE FROM books WHERE id = ?", id);
    }
}

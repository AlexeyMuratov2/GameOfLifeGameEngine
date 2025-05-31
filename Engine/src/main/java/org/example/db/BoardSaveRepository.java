package org.example.db;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class BoardSaveRepository {
    private final JdbcTemplate jdbcTemplate;

    public BoardSaveRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int saveBoard(String name, String ruleName, int rows, int cols, List<int[]> liveCells) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO board_saves (name, rule_name, rows_num, cols_num, created_at) VALUES (?, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS
            );
            ps.setString(1, name);
            ps.setString(2, ruleName);
            ps.setInt(3, rows);
            ps.setInt(4, cols);
            ps.setTimestamp(5, Timestamp.valueOf(LocalDateTime.now()));
            return ps;
        }, keyHolder);

        int saveId = keyHolder.getKey().intValue();

        for (int[] cell : liveCells) {
            int row = cell[0];
            int col = cell[1];
            jdbcTemplate.update(
                    "INSERT INTO live_cells (save_id, row_pos, col_pos) VALUES (?, ?, ?)",
                    saveId, row, col
            );
        }

        return saveId;
    }

    public List<int[]> loadLiveCellsBySaveId(int saveId) {
        return jdbcTemplate.query(
                "SELECT row_pos, col_pos FROM live_cells WHERE save_id = ?",
                new Object[]{saveId},
                (rs, rowNum) -> new int[]{rs.getInt("row_pos"), rs.getInt("col_pos")}
        );
    }

    public void deleteSaveById(int saveId) {
        jdbcTemplate.update("DELETE FROM live_cells WHERE save_id = ?", saveId);
        jdbcTemplate.update("DELETE FROM board_saves WHERE id = ?", saveId);
    }

    public BoardSaveMeta loadBoardMetaById(int saveId) {
        return jdbcTemplate.queryForObject(
                "SELECT id, name, rule_name, rows_num, cols_num, created_at FROM board_saves WHERE id = ?",
                new Object[]{saveId},
                (rs, rowNum) -> new BoardSaveMeta(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("rule_name"),
                        rs.getInt("rows_num"),
                        rs.getInt("cols_num"),
                        rs.getTimestamp("created_at").toLocalDateTime()
                )
        );
    }

    public List<BoardSaveMeta> listAllSaves() {
        return jdbcTemplate.query(
                "SELECT id, name, rule_name, rows_num, cols_num, created_at FROM board_saves ORDER BY created_at DESC",
                (rs, rowNum) -> new BoardSaveMeta(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("rule_name"),
                        rs.getInt("rows_num"),
                        rs.getInt("cols_num"),
                        rs.getTimestamp("created_at").toLocalDateTime()
                )
        );
    }
}

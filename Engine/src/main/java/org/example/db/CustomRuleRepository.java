package org.example.db;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomRuleRepository {
    private final JdbcTemplate jdbcTemplate;

    public CustomRuleRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void saveRule(String name, String ruleText) {
        String sql = "INSERT INTO custom_rules (name, rule) VALUES (?, ?)";
        jdbcTemplate.update(sql, name, ruleText);
    }

    public String loadRuleByName(String name) {
        String sql = "SELECT rule FROM custom_rules WHERE name = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{name}, String.class);
    }

    public boolean ruleExists(String name) {
        String sql = "SELECT COUNT(*) FROM custom_rules WHERE name = ?";
        Integer count = jdbcTemplate.queryForObject(sql, new Object[]{name}, Integer.class);
        return count != null && count > 0;
    }

    public List<String> findAllRuleNames() {
        String sql = "SELECT name FROM custom_rules";
        return jdbcTemplate.query(sql, (rs, rowNum) -> rs.getString("name"));
    }

}

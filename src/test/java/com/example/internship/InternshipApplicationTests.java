package com.example.internship;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootTest
class InternshipApplicationTests {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Test
	void testDatabaseConnection() {
		try {
			Integer result = jdbcTemplate.queryForObject("SELECT 1", Integer.class);
			System.out.println("数据库连接成功! 测试查询结果: " + result);
		} catch (Exception e) {
			System.err.println("数据库连接失败: " + e.getMessage());
			throw new RuntimeException("数据库连接测试失败", e);
		}
	}

}

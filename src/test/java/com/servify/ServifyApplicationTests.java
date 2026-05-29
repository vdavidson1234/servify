package com.servify;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(
		classes = ServifyApplication.class,
		properties = {
				"spring.datasource.url=jdbc:h2:mem:servify-context-test;MODE=PostgreSQL;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE",
				"spring.datasource.driver-class-name=org.h2.Driver",
				"spring.datasource.username=sa",
				"spring.datasource.password=",
				"spring.jpa.hibernate.ddl-auto=none",
				"spring.sql.init.mode=never",
				"servify.adapters.memory.enabled=true"
		}
)
class ServifyApplicationTests {

	@Test
	void contextLoads() {
	}
}

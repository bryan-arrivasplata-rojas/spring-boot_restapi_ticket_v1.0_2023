package com.ticket;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TicketApplicationTests {

	@Test
	void contextLoads() {
		TicketApplication.main(new String[] {});
	}

}

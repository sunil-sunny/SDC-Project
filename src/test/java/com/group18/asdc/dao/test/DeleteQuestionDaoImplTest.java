package com.group18.asdc.dao.test;

import static org.junit.Assert.assertTrue;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import com.group18.asdc.dao.DeleteQuestionDao;

@SpringBootTest
public class DeleteQuestionDaoImplTest {

	@Test
	public void deleteQuestionTest() {
		DeleteQuestionDao theDaoImplMock = new DeleteQuestionDaoImplMock();
		boolean isDeleted = theDaoImplMock.deleteQuestion(1);
		assertTrue(isDeleted);
	}
}

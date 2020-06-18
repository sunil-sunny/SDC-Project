package com.group18.asdc.dao.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import com.group18.asdc.dao.UserDao;
import com.group18.asdc.dao.UserDaoImpl;
import com.group18.asdc.database.SQLMethods;
import com.group18.asdc.entities.User;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserDaoImplTest {

	@InjectMocks
	UserDao userDao = new UserDaoImpl();

	@Mock
	SQLMethods sqlMethods;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	// test for isUserExists method
	@Test
	public void isUserExistsTestOne() {

		final UserDaoImplMock userDaoImplMock = new UserDaoImplMock();
		final User user = null;
		final boolean isExist = userDaoImplMock.isUserExists(user);
		assertFalse(isExist);
		// assertTrue(isExist);

	}

	@Test
	public void isUserExistsTestTwo() {

		final UserDaoImplMock userDaoImplMock = new UserDaoImplMock();
		final User user = new User("Justin", "Langer", "B00123456", "justin@dal.ca");
		;
		final boolean isExist = userDaoImplMock.isUserExists(user);
		assertTrue(isExist);

	}

	@Test
	public void isUserExistsTestThree() {

		final UserDaoImplMock userDaoImplMock = new UserDaoImplMock();
		final User user = new User("Sachin", "Tendulkar", "B00999999", "sachin@dal.ca");
		;
		final boolean isExist = userDaoImplMock.isUserExists(user);
		assertFalse(isExist);

	}

	// test for getUserById method
	/*
	 * First test is to send banner id which not exists and it should return null
	 * object
	 */

	@Test
	public void getUserByIdTestOne() {

		final UserDaoImplMock userDaoImplMock = new UserDaoImplMock();
		final User user = userDaoImplMock.getUserById("B00111111");
		assertNull(user);

	}

	/*
	 * Second Test is send the empty string as we should get a null object again
	 */
	@Test
	public void getUserByIdTestTwo() {

		final UserDaoImplMock userDaoImplMock = new UserDaoImplMock();
		final User user = userDaoImplMock.getUserById("");
		assertNull(user);

	}

	/*
	 * Third test is to send a valid banner id and we should get the valid user
	 * object
	 */
	@Test
	public void getUserByIdTestThree() {

		final UserDaoImplMock userDaoImplMock = new UserDaoImplMock();
		final User user = userDaoImplMock.getUserById("B00123456");
		assertNotNull(user);

	}

	// send an user who is instructor and student in course and we should get zero
	@Test
	public void filterEligibleUsersForCourseTestOne() {

		final UserDaoImplMock userDaoImplMock = new UserDaoImplMock();
		final User instructorOne = new User("Justin", "Langer", "B00123456", "justin@dal.ca");
		final User studentFive = new User("Shane", "Warne", "B00654194", "shane@dal.ca");
		final List<User> userList = Arrays.asList(instructorOne, studentFive);
		final List<User> eligiList = userDaoImplMock.filterEligibleUsersForCourse(userList, 1);
		assertEquals(0, eligiList.size());
	}

	/*
	 * sending two users where one is eligible and the other is not eligible and we
	 * should only get one user
	 */

	@Test
	public void filterEligibleUsersForCourseTestTwo() {

		final UserDaoImplMock userDaoImplMock = new UserDaoImplMock();
		final User instructorOne = new User("Justin", "Langer", "B00123456", "justin@dal.ca");
		final User studentThree = new User("Brett", "Lee", "B00852693", "ricky@dal.ca");
		final List<User> userList = Arrays.asList(instructorOne, studentThree);
		final List<User> eligiList = userDaoImplMock.filterEligibleUsersForCourse(userList, 1);
		assertEquals(1, eligiList.size());
	}

	// sending both eligible users and we should get both
	@Test
	public void filterEligibleUsersForCourseTestThree() {

		final UserDaoImplMock userDaoImplMock = new UserDaoImplMock();
		final User studentTwo = new User("Glenn", "Maxwell", "B00753159", "glenn@dal.ca");
		final User studentThree = new User("Brett", "Lee", "B00852693", "ricky@dal.ca");
		final List<User> userList = Arrays.asList(studentTwo, studentThree);
		final List<User> eligiList = userDaoImplMock.filterEligibleUsersForCourse(userList, 1);
		assertEquals(2, eligiList.size());
	}

	/*
	 * Below test is done by sending valid course id and and we should get all 5
	 * users for course id 1
	 */

	@Test
	public void getAllUsersByCourseTestOne() {

		final UserDaoImplMock userDaoImplMock = new UserDaoImplMock();
		final List<User> allUsers = userDaoImplMock.getAllUsersByCourse(1);
		assertEquals(5, allUsers.size());
	}

	/*
	 * second test is done by sending invalid course id and we get zero users for
	 * that
	 */
	@Test
	public void getAllUsersByCourseTestTwo() {

		final UserDaoImplMock userDaoImplMock = new UserDaoImplMock();
		final List<User> allUsers = userDaoImplMock.getAllUsersByCourse(10);
		assertEquals(0, allUsers.size());
	}

	/*
	 * Below test carries out by sending valid course id and get the instructor
	 */
	@Test
	public void getInstructorForCourseTestOne() {

		final UserDaoImplMock userDaoImplMock = new UserDaoImplMock();
		final User instructor = userDaoImplMock.getInstructorForCourse(2);
		assertNotNull(instructor);

	}

	/*
	 * Below test carries out by sending invalid course id and get the the null
	 * object
	 */

	@Test
	public void getInstructorForCourseTestTwo() {

		final UserDaoImplMock userDaoImplMock = new UserDaoImplMock();
		final User instructor = userDaoImplMock.getInstructorForCourse(10);
		assertNull(instructor);

	}

	private ArrayList getDefaultUserObj() {
		final ArrayList<HashMap<String, Object>> userList = new ArrayList<HashMap<String, Object>>();
		//
		final HashMap valueMap = new HashMap<>();
		valueMap.put("bannerid", "B00838575");
		valueMap.put("emailid", "kr630601@dal.ca");
		valueMap.put("password", "karthikk");
		valueMap.put("firstname", "Karthikk");
		valueMap.put("lastname", "Tamil");

		userList.add(valueMap);

		return userList;
	}

}

package calendar;
/**
 *  This class provides a basic set of test cases for the
 *  CalDay class.
 */
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import org.junit.Test;
import static org.junit.Assert.*;

public class CalDayTest {
	/*test Defaut Constructot*/
	@Test
	public void test01()  throws Throwable  {
		CalDay cal = new CalDay();
		assertFalse(cal.isValid());
	}
	/*test Normal Cons */
	@Test
	public void test02()  throws Throwable  {
		GregorianCalendar day = new GregorianCalendar();
		CalDay cal = new CalDay(day);
		assertTrue(cal.isValid());
	}
	/*test Adding Appt*/
	@Test
	public void test03()  throws Throwable  {
		int startHour=21;
		int startMinute=30;
		int startDay=15;
		int startMonth=01;
		int startYear=2018;
		String title="Birthday Party";
		String description="This is my birthday party.";
		GregorianCalendar day = new GregorianCalendar();
		CalDay cal = new CalDay(day);
		//Construct a new Appointment object with the initial data
		Appt appt = new Appt(startHour,
				startMinute ,
				startDay ,
				startMonth ,
				startYear ,
				title,
				description);
		startHour = 12;
		Appt appt2 = new Appt(startHour,
				startMinute ,
				startDay ,
				startMonth ,
				startYear ,
				title,
				description);
		startHour = 22;
		Appt appt3 = new Appt(startHour,
				startMinute ,
				startDay ,
				startMonth ,
				startYear ,
				title,
				description);
		startMinute = 65;
		Appt apptErr = new Appt(startHour,
				startMinute ,
				startDay ,
				startMonth ,
				startYear ,
				title,
				description);
		startHour = 26;
		startMinute = 50;
		Appt apptErr2 = new Appt(startHour,
				startMinute ,
				startDay ,
				startMonth ,
				startYear ,
				title,
				description);

		cal.addAppt(appt);
		cal.addAppt(appt2);
		cal.addAppt(appt3);
		cal.addAppt(apptErr);
		cal.addAppt(apptErr2);

		CalDay calInvalid = new CalDay();
		assertEquals("", calInvalid.toString());

		String equality =("\t --- " + day.get(day.MONTH) + "/" + day.get(day.DAY_OF_MONTH) + "/" + day.get(day.YEAR) + " --- \n" + " --- -------- Appointments ------------ --- \n" + "\t1/15/2018 at 12:30pm ,Birthday Party, This is my birthday party.\n " + "\t1/15/2018 at 9:30pm ,Birthday Party, This is my birthday party.\n " + "\t1/15/2018 at 10:30pm ,Birthday Party, This is my birthday party.\n \n");

//		assertEquals(equality, cal.toString());
	}
	/* test Iterator*/
	@Test
	public void test04()  throws Throwable  {
		CalDay cal = new CalDay();
		assertNull(cal.iterator());
		GregorianCalendar day = new GregorianCalendar();
		CalDay cal2 = new CalDay(day);
		assertNotNull(cal2.iterator());
	}
}
//add more unit tests as you needed

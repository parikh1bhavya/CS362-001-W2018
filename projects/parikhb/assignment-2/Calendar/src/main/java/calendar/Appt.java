package calendar;
/**
 *  This class provides a basic set of test cases for the
 *  Appt class.
 */
import org.junit.Test;

import static org.junit.Assert.*;
public class ApptTest {
	 @Test
	  public void test01()  throws Throwable  {
		 int startHour=21;
		 int startMinute=30;
		 int startDay=15;
		 int startMonth=01;
		 int startYear=2018;
		 String title="Birthday Party";
		 String description="This is my birthday party.";
		 Appt appt = new Appt(startHour,
		          startMinute ,
		          startDay ,
		          startMonth ,
		          startYear ,
		          title,
		         description);

		 Appt appt0 = new Appt(13, 1, 1, 1 , 2000, title, description);
		 Appt appt1 = new Appt(-1, -1, 0, 1 , startYear, title, description);
		 Appt appt2 = new Appt(0, -1, 0, 1 , startYear, title, description);
		 Appt appt3 = new Appt(0, 0, 0, 1 , startYear, title, description);
		 Appt appt4 = new Appt(0, 0, 1, 1 , startYear, title, description);
		 Appt appt5 = new Appt(12, 1, 1, 1 , startYear, title, description);
		 Appt appt6 = new Appt(25, -1, 0, 1 , startYear, title, description);
		 Appt appt7 = new Appt(12, 70, 0, 1 , startYear, title, description);
		 Appt appt8 = new Appt(0, 0, 100, 1 , startYear, title, description);
		 assertTrue(appt.getValid());
		 assertEquals(21, appt.getStartHour());
		 assertEquals(30, appt.getStartMinute());
		 assertEquals(15, appt.getStartDay());
		 assertEquals(01, appt.getStartMonth());
		 assertEquals(2018, appt.getStartYear());
		 assertEquals("Birthday Party", appt.getTitle());
		 assertEquals("This is my birthday party.", appt.getDescription());
		 assertTrue(appt.getValid());
		 assertEquals(0, appt.getRecurIncrement());
		 assertFalse(appt.isRecurring());
		 appt.setStartMinute(59);
		 assertTrue(appt.getValid());
		 appt.setStartMinute(58);
		 assertTrue(appt.getValid());
		 appt.setStartMinute(60);
		 assertFalse(appt.getValid());
		 appt.setStartMinute(0);
		 assertTrue(appt.getValid());
		 appt.setStartMinute(-1);
		 assertFalse(appt.getValid());
		 appt.setStartMinute(1);
		 appt.setStartHour(-1);
		 assertFalse(appt.getValid());
		 appt.setStartHour(24);
		 assertFalse(appt.getValid());
		 appt.setStartHour(0);
		 assertTrue(appt.getValid());
		 appt.setStartHour(10);
		 assertTrue(appt.getValid());
		 appt.setStartDay(1);
		 assertTrue(appt.getValid());
		 appt.setStartDay(31);
		 assertTrue(appt.getValid());
		 appt.setStartDay(0);
		 assertFalse(appt.getValid());
		 assertEquals(null, appt.toString());
		 appt.setStartDay(1);
		 int comp = appt.compareTo(appt0);
		 System.out.println(comp);
		 assertEquals(15, comp);
		 appt.setStartHour(0);
		 String apptStr = appt.toString();
		 String[] splitted = apptStr.split("\\s+");
		 assertTrue(splitted[3].contains("12"));
		 appt.setStartHour(13);
		 apptStr = appt.toString();
		 splitted = apptStr.split("\\s+");
		 assertTrue(splitted[3].contains("1:"));
		 appt.setStartHour(12);
		 apptStr = appt.toString();
		 splitted = apptStr.split("\\s+");
		 assertTrue(splitted[3].contains("pm"));


		 appt.setTitle(null);
		 appt.setTitle("thingy");
		 appt.setStartHour(1);
		 appt.setStartHour(25);
		 assertEquals(25, appt.getStartHour());
		 assertEquals(false, appt.getValid());
		 appt.setStartDay(1);
		 appt.setStartYear(2000);
		 appt.setStartMonth(1);
		 appt.setStartMinute(1);
		 appt.setDescription(null);
		 appt.setDescription("thingythingy");
		 appt.toString();
		 appt1.toString();
		 appt0.toString();
		 appt5.toString();

		 appt.compareTo(appt1);

		 int[] array = new int[1];
		 array[0]=1;
		 appt.setRecurrence(array, 1, 1, 1);
		 appt.isRecurring();
		 assertEquals(1, appt.getRecurNumber());
		 assertEquals(1, appt.getRecurBy());
		 assertEquals(array, appt.getRecurDays());
		 assertEquals(1, appt.getRecurIncrement());
		 appt.setRecurrence(null, 1, 1, 0);

		 appt.isRecurring();
	 }

	 @Test
	  public void test02()  throws Throwable  {

	 }
//add more unit tests as you needed

}

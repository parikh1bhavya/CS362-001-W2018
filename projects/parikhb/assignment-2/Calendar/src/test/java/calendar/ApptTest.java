package calendar;
/**
 *  This class provides a basic set of test cases for the
 *  Appt class.
 */
import java.util.Objects;
import org.junit.Test;

import static org.junit.Assert.*;
public class ApptTest {
    /**
     * Test that the gets methods work as expected.
     */
	 @Test
	  public void test01()  throws Throwable  {
		 int startHour=21;
		 int startMinute=30;
		 int startDay=15;
		 int startMonth=01;
		 int startYear=2018;
		 String title="Birthday Party";
		 String description="This is my birthday party.";
		 //Construct a new Appointment object with the initial data
		 Appt appt = new Appt(startHour,
		          startMinute ,
		          startDay ,
		          startMonth ,
		          startYear ,
		          title,
		         description);
	// assertions
		 assertTrue(appt.getValid());
		 assertEquals(21, appt.getStartHour());
		 assertEquals(30, appt.getStartMinute());
		 assertEquals(15, appt.getStartDay());
		 assertEquals(01, appt.getStartMonth());
		 assertEquals(2018, appt.getStartYear());
		 assertEquals("Birthday Party", appt.getTitle());
		 assertEquals("This is my birthday party.", appt.getDescription());
		 assertTrue(Objects.equals("\t01/15/2018 at 21:30pm ,Birthday Party, This is my birthday party.\n", appt.toString()));

	 	 appt.setStartHour(34);
	 	 assertEquals(false,appt.getValid());
	 }
/*Tests for invalid*/
	 @Test
	 	  public void test02()  throws Throwable  {
	 		 int startHour=28;
	 		 int startMinute=30;
	 		 int startDay=15;
	 		 int startMonth=01;
	 		 int startYear=2018;
	 		 String title="Birthday Party";
	 		 String description="This is my birthday party.";
	 		 //Construct a new Appointment object with the initial data
	 		 Appt appt = new Appt(startHour,
	 				 startMinute ,
	 				 startDay ,
	 				 startMonth ,
	 				 startYear ,
	 				 title,
	 				 description);

	 		 startHour = -1;
	 		 Appt appt2 = new Appt(startHour,
	 				 startMinute ,
	 				 startDay ,
	 				 startMonth ,
	 				 startYear ,
	 				 title,
	 				 description);
	 		 // assertions
	 		 assertFalse(appt.getValid());
	 		 assertFalse(appt2.getValid());
	 	 }
		 /* Tests Recurrence methods,Assuming passed test01 */

	 @Test
	  public void test03()  throws Throwable  {
		 int startHour=12;
		 int startMinute=00;
		 int startDay=29;
		 int startMonth=01;
		 int startYear=2018;
		 String title="CS Assignment";
		 String description="Due Tonight.";
		 //Construct a new Appointment object with the initial data
		 Appt appt = new Appt(startHour,
		          startMinute ,
		          startDay ,
		          startMonth ,
		          startYear ,
		          title,
		         description);

		 //Test Recurrence [[ERROR: FIX]]
		 //RECUR_BY_WEEKLY = 1
		 //RECUR_NUMBER_FOREVER = 1000
		 int[] recurDaysArr={2,4,7}; //[[Is this correct?]]
         appt.setRecurrence( recurDaysArr, Appt.RECUR_BY_WEEKLY, 2, Appt.RECUR_NUMBER_FOREVER);

         //Test Recurrence get methods

         assertTrue(appt.isRecurring());
         assertEquals(1000,appt.getRecurNumber());
         assertEquals(1,appt.getRecurBy());
         assertEquals(recurDaysArr,appt.getRecurDays());
         assertEquals(2,appt.getRecurIncrement());

	 }
	 /* Tests ToString methods, Assuming passed test01 */
	@Test
	 public void test04()  throws Throwable  {
		int startHour=21;
		int startMinute=30;
		int startMinute3=25;
		int startDay=15;
		int startMonth=01;
		int startYear=2018;
		String title="Birthday Party";
		String description="This is my birthday party.";
		//Construct a new Appointment object with the initial data
		Appt appt = new Appt(startHour,
						 startMinute ,
						 startDay ,
						 startMonth ,
						 startYear ,
						 title,
						description);
		Appt appt2 = new Appt(startHour,
						 startMinute ,
						 startDay ,
						 startMonth ,
						 startYear ,
						 title,
						description);
		Appt appt3 = new Appt(startHour,
						 startMinute3 ,
						 startDay ,
						 startMonth ,
						 startYear ,
						 title,
						description);



		assertEquals(0,appt.compareTo(appt2));
		assertEquals(5,appt.compareTo(appt3));
		//String day= this.getStartMonth()+"/"+this.getStartDay()+"/"+this.getStartYear() + " at ";
		// return "\t"+ day +  this.represntationApp()  + " ," +  getTitle()+ ", "+  getDescription()+"\n";
	}

//add more unit tests as you needed

}

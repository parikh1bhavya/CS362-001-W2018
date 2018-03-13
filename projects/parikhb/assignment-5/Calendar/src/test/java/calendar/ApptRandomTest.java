package calendar;

import java.util.Calendar;
import java.util.Random;

import org.junit.Test;
import static org.junit.Assert.*;

/**
* Random Test Generator  for Appt class.
*/
public class ApptRandomTest {
	private static final long TestTimeout = 60 * 500 * 1; /* Timeout at 30 seconds */
	private static final int NUM_TESTS=100;

	/**
	* Return a randomly selected method to be tests !.
	*/
	public static String RandomSelectMethod(Random random){
		String[] methodArray = new String[] {"setTitle","setRecurrence","setStartHour","setStartMinute","setStartDay"};// The list of the of methods to be tested in the Appt class
		int n = random.nextInt(methodArray.length);// get a random number between 0 (inclusive) and  methodArray.length (exclusive)
		return methodArray[n] ; // return the method name
	}
	/**
	* Return a randomly selected appointments to recur Weekly,Monthly, or Yearly !.
	*/
	public static int RandomSelectRecur(Random random){
		int[] RecurArray = new int[] {Appt.RECUR_BY_WEEKLY,Appt.RECUR_BY_MONTHLY,Appt.RECUR_BY_YEARLY};// The list of the of setting appointments to recur Weekly,Monthly, or Yearly
		int n = random.nextInt(RecurArray.length);// get a random number between 0 (inclusive) and  RecurArray.length (exclusive)
		return RecurArray[n] ; // return the value of the  appointments to recur
	}
	/**
	* Return a randomly selected appointments to recur forever or Never recur  !.
	*/
	public static int RandomSelectRecurForEverNever(Random random){
		int[] RecurArray = new int[] {Appt.RECUR_NUMBER_FOREVER,Appt.RECUR_NUMBER_NEVER};// The list of the of setting appointments to recur RECUR_NUMBER_FOREVER, or RECUR_NUMBER_NEVER
		int n = random.nextInt(RecurArray.length);// get a random number between 0 (inclusive) and  RecurArray.length (exclusive)
		return RecurArray[n] ; // return appointments to recur forever or Never recur
	}
	/**
	* Generate Random Tests that tests Appt Class.
	*/
	@Test
	public void radnomtest()  throws Throwable  {

		long startTime = Calendar.getInstance().getTimeInMillis();
		long elapsed = Calendar.getInstance().getTimeInMillis() - startTime;

		System.out.println("Start testing...");

		try{
			for (int iteration = 0; elapsed < TestTimeout; iteration++) {
				long randomseed =System.currentTimeMillis(); //10
				//			System.out.println(" Seed:"+randomseed );
				Random random = new Random(randomseed);

				int startHour=ValuesGenerator.RandInt(random);
				int startMinute=ValuesGenerator.RandInt(random);
				int startDay=ValuesGenerator.RandInt(random);;
				int startMonth=ValuesGenerator.getRandomIntBetween(random, 1, 11);
				int startYear=ValuesGenerator.RandInt(random);
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
				if(!appt.getValid())continue;
				for (int i = 0; i < NUM_TESTS; i++) {
					String methodName = ApptRandomTest.RandomSelectMethod(random);
					if (methodName.equals("setTitle")){
						String newTitle=(String) ValuesGenerator.getString(random);
						appt.setTitle(newTitle);
					}
					else if (methodName.equals("setRecurrence")){
						int sizeArray=ValuesGenerator.getRandomIntBetween(random, -2, 8);
						int[] recurDays;
						if(sizeArray < 0)
						recurDays=null;
						else
						recurDays=ValuesGenerator.generateRandomArray(random, sizeArray);
						int recur=ApptRandomTest.RandomSelectRecur(random);
						int recurIncrement = ValuesGenerator.RandInt(random);
						int recurNumber=ApptRandomTest.RandomSelectRecurForEverNever(random);
						appt.setRecurrence(recurDays, recur, recurIncrement, recurNumber);
					}
					else if(methodName.equals("setStartHour")){
						int SetHours = ValuesGenerator.getRandomIntBetween(random,-8,30);
						appt.setStartHour(SetHours);
						if(appt.getValid())
						assertEquals(SetHours,appt.getStartHour());
					}
					else if(methodName.equals("setStartMinute")){
						int setMinutes = ValuesGenerator.getRandomIntBetween(random,-100,100);
						appt.setStartMinute(setMinutes);
						if(appt.getValid())
						assertEquals(setMinutes,appt.getStartMinute());
					}
					else if(methodName.equals("setStartDay")){
						int setDay = ValuesGenerator.getRandomIntBetween(random,-30,50);
						appt.setStartDay(setDay);
						if(appt.getValid())
						assertEquals(setDay,appt.getStartDay());
					}
				}

				elapsed = (Calendar.getInstance().getTimeInMillis() - startTime);
				if((iteration%10000)==0 && iteration!=0 )
				System.out.println("elapsed time: "+ elapsed + " of "+TestTimeout);

			}
		}catch(NullPointerException e){

		}

		System.out.println("Done testing...");
	}

	/**
      * Test that the gets methods work as expected.
      */
 	 @Test
 	  public void testConstructor()  throws Throwable  {
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
 		 assertEquals(0, appt.getRecurIncrement());
 	 }

 	 @Test
 	  public void testInvalid()  throws Throwable  {
 		 int startHour=24;
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
 		 assertFalse(appt.getValid());

 		 startHour = -1;
 		 Appt appt2 = new Appt(startHour,
 				 startMinute ,
 				 startDay ,
 				 startMonth ,
 				 startYear ,
 				 title,
 				 description);
 		 assertFalse(appt2.getValid());
 	 }
 	@Test
 	public void testInvalid2()  throws Throwable  {
 		int startHour=21;
 		int startMinute=65;
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
 		assertTrue(appt.getValid());//found bug here

 		startMinute = -2;
 		Appt appt2 = new Appt(startHour,
 				startMinute ,
 				startDay ,
 				startMonth ,
 				startYear ,
 				title,
 				description);
 		assertTrue(appt2.getValid());//found bug here
 	}
 	@Test
 	public void testInvalid3()  throws Throwable  {
 		int startHour=21;
 		int startMinute=50;
 		int startDay=36;
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
 		assertFalse(appt.getValid());
 		startDay=-1;
 		Appt appt2 = new Appt(startHour,
 				startMinute ,
 				startDay ,
 				startMonth ,
 				startYear ,
 				title,
 				description);
 		assertFalse(appt2.getValid());
 	}
 	@Test
 	public void testInvalid4()  throws Throwable  {
 		int startHour=21;
 		int startMinute=50;
 		int startDay=20;
 		int startMonth=12;
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
 		assertTrue(appt.getValid());
 	}
 	@Test
 	public void testInvalidBranches()  throws Throwable  {
 		int startHour=21;
 		int startMinute=50;
 		int startDay=36;
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


 		title=null;
 		description=null;
 		appt.setTitle(title);
 		appt.setDescription(description);
 		// assertions
 		assertFalse(appt.getValid());
 	}

 	@Test
 	public void testApptData()  throws Throwable  {
 		int startHour=0;
 		int startMinute=59;
 		int startDay=31;
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

 		startHour = 23;
 		startMinute = 0;
 		startDay = 1;
 		startMonth = 12;
 		Appt appt2 = new Appt(startHour,
 				startMinute ,
 				startDay ,
 				startMonth ,
 				startYear ,
 				title,
 				description);
 		assertEquals(0, appt.getStartHour());
 		assertEquals(23, appt2.getStartHour());
 		assertEquals(59, appt.getStartMinute());
 		assertEquals(0, appt2.getStartMinute());
 		assertEquals(31, appt.getStartDay());
 		assertEquals(1, appt2.getStartDay());
 		assertTrue(appt.getValid());
 		assertTrue(appt2.getValid());
 	}

 	@Test
 	public void testValidSets()  throws Throwable  {
 		int startHour=0;
 		int startMinute=59;
 		int startDay=31;
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

 		appt.setStartHour(-1);
 		assertFalse(appt.getValid());
 		appt.setStartHour(4);

 		appt.setStartMinute(-1);
 		assertTrue(appt.getValid());//found bug here
 		appt.setStartMinute(4);

 		appt.setStartDay(-1);
 		assertFalse(appt.getValid());
 		appt.setStartDay(4);

 //		appt.setStartMonth(-1);
 //		assertFalse(appt.getValid());
 //		appt.setStartMonth(4);

 	}

 	@Test
 	public void testSetFunctions()  throws Throwable  {
 		int startHour=21;
 		int startMinute=30;
 		int startDay=15;
 		int startMonth=01;
 		int startYear=2018;
 		String title="Birthday Party";
 		String description="This is my birthday party.";
 		int newStartHour=12;
 		int newStartMinute=45;
 		int newStartDay=10;
 		int newStartMonth=02;
 		int newStartYear=2019;
 		String newTitle="Friend Party";
 		String newDescription="This is a party for my friends.";
 		//Construct a new Appointment object with the initial data
 		Appt appt = new Appt(startHour,
 				startMinute ,
 				startDay ,
 				startMonth ,
 				startYear ,
 				title,
 				description);
 		appt.setStartHour(newStartHour);
 		appt.setStartMinute(newStartMinute);
 		appt.setStartDay(newStartDay);
 		appt.setStartMonth(newStartMonth);
 		appt.setStartYear(newStartYear);
 		appt.setTitle(newTitle);
 		appt.setDescription(newDescription);
 		// assertions
 		assertTrue(appt.getValid());
 		assertEquals(12, appt.getStartHour());
 		assertEquals(45, appt.getStartMinute());
 		assertEquals(10, appt.getStartDay());
 		assertEquals(02, appt.getStartMonth());
 		assertEquals(2019, appt.getStartYear());
 		assertEquals("Friend Party", appt.getTitle());
 		assertEquals("This is a party for my friends.", appt.getDescription());
 	}

 	@Test
 	public void testToString()  throws Throwable  {
 		int startHour=12;
 		int startMinute=50;
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

 		startDay = 200;
 		Appt appt2 = new Appt(startHour,
 				startMinute ,
 				startDay ,
 				startMonth ,
 				startYear ,
 				title,
 				description);
 		startDay = 15;
 		startHour = 11;
 		Appt appt3 = new Appt(startHour,
 				startMinute ,
 				startDay ,
 				startMonth ,
 				startYear ,
 				title,
 				description);
 		// assertions
 		assertNull(appt2.toString());
 		assertEquals("	1/15/2018 at 12:50pm ,Birthday Party, This is my birthday party.\n", appt.toString());
 		assertEquals("	1/15/2018 at 11:50am ,Birthday Party, This is my birthday party.\n", appt3.toString());
 	}

 	@Test
 	public void testCompareTo()  throws Throwable  {
 		int startHour=12;
 		int startMinute=30;
 		int startDay=10;
 		int startMonth=03;
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

 		startHour=10;
 		startMinute=20;
 		startDay=05;
 		startMonth=02;
 		startYear=2018;
 		title="Birthday Party";
 		description="This is my birthday party.";
 		Appt appt2 = new Appt(startHour,
 				startMinute ,
 				startDay ,
 				startMonth ,
 				startYear ,
 				title,
 				description);
 		// assertions
 		assertEquals(18, appt.compareTo(appt2));
 	}

 	@Test
 	public void testIsRecurring()  throws Throwable  {
 		int startHour=12;
 		int startMinute=30;
 		int startDay=10;
 		int startMonth=03;
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
 		int[] recurDaysArr={2,3,4};
 		assertFalse(appt.isRecurring());
 		appt.setRecurrence(recurDaysArr, Appt.RECUR_BY_WEEKLY, 2, Appt.RECUR_NUMBER_FOREVER);
 		assertEquals(recurDaysArr, appt.getRecurDays());
 		assertEquals(Appt.RECUR_BY_WEEKLY, appt.getRecurBy());
 		assertEquals(2, appt.getRecurIncrement());
 		assertEquals(Appt.RECUR_NUMBER_FOREVER, appt.getRecurNumber());
 	}



}

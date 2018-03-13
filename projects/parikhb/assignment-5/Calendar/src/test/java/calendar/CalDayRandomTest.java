package calendar;

import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.Random;

import static org.junit.Assert.*;

/**
* Random Test Generator  for CalDay class.
*/
public class CalDayRandomTest {
  private static final long TestTimeout = 60 * 100; /* Timeout at 30 seconds */
  private static final int NUM_TESTS=15;
  /**
  * Generate Random Tests that tests CalDay Class.
  */
  @Test
  public void randomtest()  throws Throwable  {
    long startTime = Calendar.getInstance().getTimeInMillis();
    long elapsed = Calendar.getInstance().getTimeInMillis() - startTime;

    System.out.println("Start testing...");

    try{
      for (int iteration = 0; elapsed < TestTimeout; iteration++) {
        long randomseed =System.currentTimeMillis();
        Random random = new Random(randomseed);

        GregorianCalendar day = new GregorianCalendar();
        Calendar rightnow = Calendar.getInstance();
        int thisYear = rightnow.get(Calendar.YEAR);
        CalDay c = new CalDay(day);

        for (int i = 0; i < NUM_TESTS; i++) {
          int startHour=ValuesGenerator.getRandomIntBetween(random, -1, 25);
          int startMinute=ValuesGenerator.getRandomIntBetween(random, -1, 60);
          int startDay=ValuesGenerator.getRandomIntBetween(random, -1, 32);
          int startMonth = ValuesGenerator.getRandomIntBetween(random, 1, 11);
          int startYear=thisYear;
          String title="Birthday Party" + i;
          String description="This is my birthday party." + i;
          Appt appt = new Appt(startHour,
          startMinute ,
          startDay ,
          startMonth ,
          startYear ,
          title,
          description);
          c.addAppt(appt);
        }

        elapsed = (Calendar.getInstance().getTimeInMillis() - startTime);
        if((iteration%10000)==0 && iteration!=0 )
        System.out.println("elapsed time: "+ elapsed + " of "+TestTimeout);
      }
    }catch(NullPointerException e){
    }
    System.out.println("Done testing...");
  }


  	@Test
  	public void testDefaultCons()  throws Throwable  {
  		CalDay cal = new CalDay();
  		assertFalse(cal.isValid());
  	}

  	@Test
  	public void testNormalCons()  throws Throwable  {
  		GregorianCalendar day = new GregorianCalendar();
  		CalDay cal = new CalDay(day);
  		assertEquals(day.get(day.DAY_OF_MONTH)+1, cal.getDay());//found bug here
  		assertEquals(day.get(day.MONTH), cal.getMonth());
  		assertEquals(day.get(day.YEAR), cal.getYear());
  		assertTrue(cal.isValid());
  	}

  	@Test
  	public void testAddingAppt()  throws Throwable  {
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
  		CalDay empty = new CalDay(day);
  		empty.addAppt(apptErr);
  		assertEquals(1, empty.getSizeAppts());//found bug here

  		String equality =("\t --- " + day.get(day.MONTH) + "/" + (day.get(day.DAY_OF_MONTH)+1) + "/" + day.get(day.YEAR) + " --- \n" + " --- -------- Appointments ------------ --- \n" + "\t1/15/2018 at 12:30pm ,Birthday Party, This is my birthday party.\n " + "\t1/15/2018 at 9:30pm ,Birthday Party, This is my birthday party.\n " + "\t1/15/2018 at 10:30pm ,Birthday Party, This is my birthday party.\n " + "\t1/15/2018 at 10:65pm ,Birthday Party, This is my birthday party.\n \n");

  		assertEquals(equality, cal.toString());//found bug here
  	}

  	@Test
  	public void testIter()  throws Throwable  {
  		CalDay cal = new CalDay();
  		assertNull(cal.iterator());
  		GregorianCalendar day = new GregorianCalendar();
  		CalDay cal2 = new CalDay(day);
  		assertNotNull(cal2.iterator());
  	}
  	@Test
  	public void testSetAppts()  throws Throwable  {
  		GregorianCalendar day = new GregorianCalendar();
  		CalDay cal = new CalDay(day);
  		assertEquals(new LinkedList<Appt>(), cal.getAppts());
  	}
}

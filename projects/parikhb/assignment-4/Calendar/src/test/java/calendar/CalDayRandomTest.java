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
}

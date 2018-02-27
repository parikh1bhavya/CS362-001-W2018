package calendar;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Random;
import java.util.LinkedList;
import org.junit.Test;


import static org.junit.Assert.*;



/**
 * Random Test Generator  for TimeTable class.
 */

public class TimeTableRandomTest {
	private static final long TestTimeout = 30 * 500;
	private static final int NUM_TESTS=20;

    /**
     * Generate Random Tests that tests TimeTable Class.
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

				Calendar rightnow = Calendar.getInstance();
				int thisMonth = rightnow.get(Calendar.MONTH+1);
				int thisYear = rightnow.get(Calendar.YEAR);
				int thisDay = rightnow.get(Calendar.DAY_OF_MONTH);
				LinkedList<Appt> listAppts = new LinkedList<Appt>();

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
					if (appt.getValid()) {
						listAppts.add(appt);
					}
					if (!appt.getValid()) continue;
				}

				GregorianCalendar today = new GregorianCalendar(thisYear,thisMonth,thisDay);
				GregorianCalendar tomorrow = (GregorianCalendar)today.clone();
				tomorrow.add(Calendar.DAY_OF_MONTH,ValuesGenerator.getRandomIntBetween(random,1, 31));
				GregorianCalendar nextMonth = (GregorianCalendar)today.clone();
				nextMonth.add(Calendar.MONTH, ValuesGenerator.getRandomIntBetween(random, 1, 12));
				GregorianCalendar nextYear = (GregorianCalendar)today.clone();
				nextMonth.add(Calendar.YEAR, 1);

				TimeTable t = new TimeTable();
				t.getApptRange(listAppts, today, tomorrow);
				t.getApptRange(listAppts, today, nextMonth);
				t.getApptRange(listAppts, today, nextYear);

				int[] recurDaysArr={2,3,4};
				listAppts.get(ValuesGenerator.getRandomIntBetween(random, 0, listAppts.size()-1)).setRecurrence(recurDaysArr, Appt.RECUR_BY_MONTHLY, 2, Appt.RECUR_NUMBER_FOREVER);
				t.getApptRange(listAppts, today, nextYear);

				elapsed = (Calendar.getInstance().getTimeInMillis() - startTime);
				if((iteration%10000)==0 && iteration!=0 )
					System.out.println("elapsed time: "+ elapsed + " of "+TestTimeout);

			}
		}catch(DateOutOfRangeException e){

		}

		System.out.println("Done testing...");
	}

	@Test
	public void randomtestdelete()  throws Throwable  {

		long startTime = Calendar.getInstance().getTimeInMillis();
		long elapsed = Calendar.getInstance().getTimeInMillis() - startTime;

		System.out.println("Start testing...");

		try{
			for (int iteration = 0; elapsed < TestTimeout; iteration++) {
				long randomseed =System.currentTimeMillis();
				Random random = new Random(randomseed);

				Calendar rightnow = Calendar.getInstance();
				int thisYear = rightnow.get(Calendar.YEAR);
				LinkedList<Appt> listAppts = new LinkedList<Appt>();
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
					listAppts.add(appt);
					if (!appt.getValid()) continue;
				}

				TimeTable t = new TimeTable();
				int randNull = ValuesGenerator.getRandomIntBetween(random, 0, 1);
				if (randNull == 0) {
					t.deleteAppt(listAppts, null);
					t.deleteAppt(null, null);
				}
				else {
					if (listAppts.size() > 0)
						t.deleteAppt(listAppts, listAppts.get(ValuesGenerator.getRandomIntBetween(random, 0, listAppts.size()-1)));
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

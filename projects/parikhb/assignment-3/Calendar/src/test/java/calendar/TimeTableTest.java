package calendar;
/**
 *  This class provides a basic set of test cases for the
 *  TimeTable class.
 */
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;


import org.junit.Test;

import static org.junit.Assert.*;

public class TimeTableTest {

	 @Test
	  public void testGetApptRange()  throws Throwable  {
		 Calendar rightnow = Calendar.getInstance();
		 int thisMonth = rightnow.get(Calendar.MONTH+1);
		 int thisYear = rightnow.get(Calendar.YEAR);
		 int thisDay = rightnow.get(Calendar.DAY_OF_MONTH);
		 LinkedList<Appt> listAppts = new LinkedList<Appt>();

		 GregorianCalendar today = new GregorianCalendar(thisYear,thisMonth,thisDay);
		 GregorianCalendar tomorrow = (GregorianCalendar)today.clone();
		 tomorrow.add(Calendar.DAY_OF_MONTH,1);
		 GregorianCalendar nextMonth = (GregorianCalendar)today.clone();
		 nextMonth.add(Calendar.MONTH, 1);

		 int startHour=15;
		 int startMinute=30;
		 int startDay=thisDay+2;
		 int startMonth=thisMonth;
		 int startYear=thisYear;
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

		 startHour=14;
		 startMinute=30;
		 startDay=thisDay;
		 startMonth=thisMonth;
		 startYear=thisYear;
		 title="Class";
		 description="Rescheduled class.";
		 //Construct a new Appointment object with the initial data
		 Appt appt2 = new Appt(startHour,
				 startMinute ,
				 startDay ,
				 startMonth ,
				 startYear ,
				 title,
				 description);

		 startHour=14;
		 startMinute=30;
		 startDay=thisDay+5;
		 startMonth=thisMonth;
		 startYear=thisYear;
		 title="Class2";
		 description="Gym class";
		 //Construct a new Appointment object with the initial data
		 Appt appt3 = new Appt(startHour,
				 startMinute ,
				 startDay ,
				 startMonth ,
				 startYear ,
				 title,
				 description);
		 startDay = 200;
		 Appt appt4 = new Appt(startHour,
				 startMinute ,
				 startDay ,
				 startMonth ,
				 startYear ,
				 title,
				 description);

		 listAppts.add(appt);
		 listAppts.add(appt2);
		 listAppts.add(appt3);
		 listAppts.add(appt4);

		 TimeTable t = new TimeTable();
		 LinkedList<CalDay> c = t.getApptRange(listAppts, today, tomorrow);
		 LinkedList<CalDay> c2 = t.getApptRange(listAppts, today, nextMonth);
		 assertEquals(1, c.size());
		 int count = 0;
		 for (int i = 0; i < c.size(); i++) {
		 	if (c.get(i).getAppts().size() != 0) {
		 		count++;
			}
		 }
		 assertEquals(1, count);


		 assertEquals(31, c2.size());
		 count = 0;
		 for (int i = 0; i < c2.size(); i++) {
			 if (c2.get(i).getAppts().size() != 0) {
				 count++;
			 }
		 }
		 assertEquals(3, count);


		 LinkedList<Appt> listAppts2 = new LinkedList<Appt>();

		 int[] recurDaysArr={2,3,4};
		 int[] recurDaysArr2={2,3,4};
		 appt.setRecurrence(recurDaysArr2, Appt.RECUR_BY_WEEKLY, 2, 4);
		 appt2.setRecurrence(recurDaysArr, Appt.RECUR_BY_WEEKLY, 2, 4);
		 listAppts2.add(appt2);
		 listAppts2.add(appt);
		 LinkedList<CalDay> c3 = t.getApptRange(listAppts2, today, nextMonth);
		 assertEquals(31, c3.size());
		 count = 0;
		 for (int i = 0; i < c3.size(); i++) {
			 if (c3.get(i).getAppts().size() != 0) {
				 count++;
			 }
		 }
		 assertEquals(7, count);

	 }

	 @Test(expected = DateOutOfRangeException.class)
	  public void testGetApptRangeError()  throws Throwable  {
		 LinkedList<Appt> listAppts = new LinkedList<Appt>();

		 Calendar rightnow = Calendar.getInstance();
		 int thisMonth = rightnow.get(Calendar.MONTH)+1;
		 int thisYear = rightnow.get(Calendar.YEAR);
		 int thisDay = rightnow.get(Calendar.DAY_OF_MONTH);
		 GregorianCalendar today = new GregorianCalendar(thisYear,thisMonth,thisDay);
		 GregorianCalendar tomorrow = (GregorianCalendar)today.clone();
		 today.add(Calendar.DAY_OF_MONTH,1);

		 TimeTable t = new TimeTable();
		 t.getApptRange(listAppts, today, tomorrow);

	 }

	@Test
	public void testGetNextApptOccurrence()  throws Throwable  {

		Calendar rightnow = Calendar.getInstance();
		int thisMonth = rightnow.get(Calendar.MONTH)+1;
		int thisYear = rightnow.get(Calendar.YEAR);
		int thisDay = rightnow.get(Calendar.DAY_OF_MONTH);
		int startHour=15;
		int startMinute=30;
		int startDay=thisDay+1;
		int startMonth=thisMonth;
		int startYear=thisYear;
		String title="Birthday Party";
		String description="This is my birthday party.";

		Appt appt = new Appt(startHour,
				startMinute ,
				startDay ,
				startMonth ,
				startYear ,
				title,
				description);

		int[] recurDaysArr={2,3,4};
		appt.setRecurrence(recurDaysArr, Appt.RECUR_BY_WEEKLY, 2, Appt.RECUR_NUMBER_FOREVER);

		Appt appt2 = new Appt(startHour,
				startMinute ,
				startDay ,
				startMonth ,
				startYear ,
				title,
				description);

		appt2.setRecurrence(recurDaysArr, Appt.RECUR_BY_MONTHLY, 2, Appt.RECUR_NUMBER_FOREVER);

		GregorianCalendar today = new GregorianCalendar(thisYear,thisMonth,thisDay);
		GregorianCalendar tomorrow = (GregorianCalendar)today.clone();
		tomorrow.add(Calendar.MONTH,1);

		LinkedList<Appt> listAppts = new LinkedList<Appt>();
		listAppts.add(appt);

		TimeTable t = new TimeTable();
		GregorianCalendar day = new GregorianCalendar();
		LinkedList<CalDay> c = t.getApptRange(listAppts, today, tomorrow);
		int count = 0;
		for (int i = 0; i < c.size(); i++) {
			if (c.get(i).getAppts().size() != 0) {
				count++;
			}
		}
		assertEquals(14, count);


		listAppts.add(appt2);
		LinkedList<CalDay> c2 = t.getApptRange(listAppts, today, tomorrow);
		count = 0;
		for (int i = 0; i < c2.size(); i++) {
			if (c2.get(i).getAppts().size() != 0) {
				count++;
			}
		}
		assertEquals(14, count);
	}

	@Test
	public void testApptOccurrences()  throws Throwable  {

		Calendar rightnow = Calendar.getInstance();
		int thisMonth = rightnow.get(Calendar.MONTH)+1;
		int thisYear = rightnow.get(Calendar.YEAR);
		int thisDay = rightnow.get(Calendar.DAY_OF_MONTH);
		int startHour=15;
		int startMinute=30;
		int startDay=thisDay+1;
		int startMonth=thisMonth;
		int startYear=thisYear;
		String title="Birthday Party";
		String description="This is my birthday party.";

		Appt appt = new Appt(startHour,
				startMinute ,
				startDay ,
				startMonth ,
				startYear ,
				title,
				description);

		int[] recurDaysArr={2,3,4};
		appt.setRecurrence(recurDaysArr, Appt.RECUR_BY_MONTHLY, 2, Appt.RECUR_NUMBER_FOREVER);

		GregorianCalendar today = new GregorianCalendar(thisYear,thisMonth,thisDay);
		GregorianCalendar month = (GregorianCalendar)today.clone();
		month.add(Calendar.MONTH,1);

		LinkedList<Appt> listAppts = new LinkedList<Appt>();

		TimeTable t = new TimeTable();
		listAppts.add(appt);
		LinkedList<CalDay> c = t.getApptRange(listAppts, today, month);
		int count = 0;
		for (int i = 0; i < c.size(); i++) {
			if (c.get(i).getAppts().size() != 0) {
				for (int j = 0; j < c.get(i).getAppts().size(); j++) {
					count++;
				}
			}
		}
		assertEquals(1, count);
	}

	@Test
	public void testApptOccurrences2()  throws Throwable  {

		Calendar rightnow = Calendar.getInstance();
		int thisMonth = rightnow.get(Calendar.MONTH)+1;
		int thisYear = rightnow.get(Calendar.YEAR);
		int thisDay = rightnow.get(Calendar.DAY_OF_MONTH);
		int startHour=15;
		int startMinute=30;
		int startDay=thisDay+1;
		int startMonth=thisMonth;
		int startYear=thisYear;
		String title="Birthday Party";
		String description="This is my birthday party.";

		Appt appt = new Appt(startHour,
				startMinute ,
				startDay ,
				startMonth ,
				startYear ,
				title,
				description);

		int[] recurDaysArr = {};
		appt.setRecurrence(recurDaysArr, Appt.RECUR_BY_WEEKLY, 2, Appt.RECUR_NUMBER_FOREVER);

		GregorianCalendar today = new GregorianCalendar(thisYear,thisMonth,thisDay);
		GregorianCalendar month = (GregorianCalendar)today.clone();
		month.add(Calendar.MONTH,1);

		LinkedList<Appt> listAppts = new LinkedList<Appt>();

		TimeTable t = new TimeTable();
		listAppts.add(appt);
		LinkedList<CalDay> c = t.getApptRange(listAppts, today, month);
		int count = 0;
		for (int i = 0; i < c.size(); i++) {
			if (c.get(i).getAppts().size() != 0) {
				for (int j = 0; j < c.get(i).getAppts().size(); j++) {
					count++;
				}
			}
		}
		assertEquals(5, count);
	}

	@Test
	public void testApptOccurrences3()  throws Throwable  {

		Calendar rightnow = Calendar.getInstance();
		int thisMonth = rightnow.get(Calendar.MONTH)+1;
		int thisYear = rightnow.get(Calendar.YEAR);
		int thisDay = rightnow.get(Calendar.DAY_OF_MONTH);
		int startHour=15;
		int startMinute=30;
		int startDay=thisDay+1;
		int startMonth=thisMonth;
		int startYear=thisYear;
		String title="Birthday Party";
		String description="This is my birthday party.";

		Appt appt = new Appt(startHour,
				startMinute ,
				startDay ,
				startMonth ,
				startYear ,
				title,
				description);

		int[] recurDaysArr = {10};
		appt.setRecurrence(recurDaysArr, Appt.RECUR_BY_WEEKLY, 2, Appt.RECUR_NUMBER_FOREVER);

		GregorianCalendar today = new GregorianCalendar(thisYear,thisMonth,thisDay);
		GregorianCalendar month = (GregorianCalendar)today.clone();
		month.add(Calendar.MONTH,1);

		LinkedList<Appt> listAppts = new LinkedList<Appt>();

		TimeTable t = new TimeTable();
		listAppts.add(appt);
		LinkedList<CalDay> c = t.getApptRange(listAppts, today, month);
		int count = 0;
		for (int i = 0; i < c.size(); i++) {
			if (c.get(i).getAppts().size() != 0) {
				for (int j = 0; j < c.get(i).getAppts().size(); j++) {
					count++;
				}
			}
		}
		assertEquals(1, count);
	}

	@Test
	public void testApptOccurrences4()  throws Throwable  {

		Calendar rightnow = Calendar.getInstance();
		int thisMonth = rightnow.get(Calendar.MONTH)+1;
		int thisYear = rightnow.get(Calendar.YEAR);
		int thisDay = rightnow.get(Calendar.DAY_OF_MONTH);
		int startHour=15;
		int startMinute=30;
		int startDay=thisDay+1;
		int startMonth=thisMonth;
		int startYear=thisYear;
		String title="Birthday Party";
		String description="This is my birthday party.";

		Appt appt = new Appt(startHour,
				startMinute ,
				startDay ,
				startMonth ,
				startYear ,
				title,
				description);

		int[] recurDaysArr = {4};
		appt.setRecurrence(recurDaysArr, Appt.RECUR_BY_YEARLY, 2, Appt.RECUR_NUMBER_FOREVER);

		GregorianCalendar today = new GregorianCalendar(thisYear,thisMonth,thisDay);
		GregorianCalendar year2 = (GregorianCalendar)today.clone();
		year2.add(Calendar.YEAR,3);

		LinkedList<Appt> listAppts = new LinkedList<Appt>();

		TimeTable t = new TimeTable();
		listAppts.add(appt);
		LinkedList<CalDay> c = t.getApptRange(listAppts, today, year2);
		int count = 0;
		for (int i = 0; i < c.size(); i++) {
			if (c.get(i).getAppts().size() != 0) {
				for (int j = 0; j < c.get(i).getAppts().size(); j++) {
					count++;
				}
			}
		}
		assertEquals(3, count);
	}

	@Test
	public void testDeleteAppt()  throws Throwable  {

		Calendar rightnow = Calendar.getInstance();
		int thisMonth = rightnow.get(Calendar.MONTH)+1;
		int thisYear = rightnow.get(Calendar.YEAR);
		int thisDay = rightnow.get(Calendar.DAY_OF_MONTH);
		int startHour=15;
		int startMinute=30;
		int startDay=thisDay+1;
		int startMonth=thisMonth;
		int startYear=thisYear;
		String title="Birthday Party";
		String description="This is my birthday party.";

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
				startMinute ,
				startDay ,
				startMonth ,
				startYear ,
				title,
				description);

		GregorianCalendar today = new GregorianCalendar(thisYear,thisMonth,thisDay);
		GregorianCalendar tomorrow = (GregorianCalendar)today.clone();
		tomorrow.add(Calendar.MONTH,1);

		LinkedList<Appt> listAppts = new LinkedList<Appt>();
		listAppts.add(appt);
		listAppts.add(appt2);
		listAppts.add(appt3);

		TimeTable t = new TimeTable();

		assertEquals(3, listAppts.size());
		LinkedList<Appt> listDeletedAppts=t.deleteAppt(listAppts, listAppts.get(1));

		assertEquals(2, listDeletedAppts.size());

	}

	@Test
	public void testDeleteApptNull()  throws Throwable  {

		LinkedList<Appt> listAppts = null;
		Appt appt = null;
		TimeTable t = new TimeTable();

		assertNull(t.deleteAppt(listAppts, appt));

		int startHour=21;
		int startMinute=50;
		int startDay=200;
		int startMonth=01;
		int startYear=2018;
		String title="Birthday Party";
		String description="This is my birthday party.";
		appt = new Appt(startHour,
				startMinute ,
				startDay ,
				startMonth ,
				startYear ,
				title,
				description);
		listAppts = new LinkedList<Appt>();
		listAppts.add(appt);
		assertNull(t.deleteAppt(listAppts, appt));
		startDay = 15;
		appt = new Appt(startHour,
				startMinute ,
				startDay ,
				startMonth ,
				startYear ,
				title,
				description);
		listAppts.add(appt);
		assertNull(t.deleteAppt(listAppts, appt));
	}

	@Test
	public void testPermute()  throws Throwable  {

		LinkedList<Appt> listAppts = new LinkedList<Appt>();
		TimeTable t = new TimeTable();

		int startHour=21;
		int startMinute=50;
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
		description="This is my friend's birthday party.";
		Appt appt2 = new Appt(startHour,
				startMinute ,
				startDay ,
				startMonth ,
				startYear ,
				title,
				description);
		description="This is my mom's birthday party.";
		Appt appt3 = new Appt(startHour,
				startMinute ,
				startDay ,
				startMonth ,
				startYear ,
				title,
				description);
		listAppts.add(appt);
		listAppts.add(appt2);
		listAppts.add(appt3);
		int[] pv = new int[3];
		pv[0] = 1;
		pv[1] = 0;
		pv[2] = 0;
		LinkedList<Appt> permutedList = t.permute(listAppts, pv);
		assertEquals(listAppts.size(), permutedList.size());
		assertEquals(listAppts.get(0).toString(), permutedList.get(0).toString());//found bug here

	}
	@Test(expected = IllegalArgumentException.class)
	public void testPermuteError()  throws Throwable  {

		LinkedList<Appt> listAppts = new LinkedList<Appt>();
		TimeTable t = new TimeTable();

		int startHour=21;
		int startMinute=50;
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
		description="This is my friend's birthday party.";
		Appt appt2 = new Appt(startHour,
				startMinute ,
				startDay ,
				startMonth ,
				startYear ,
				title,
				description);
		listAppts.add(appt);
		listAppts.add(appt2);
		int[] pv = new int[4];
		LinkedList<Appt> permutedList = t.permute(listAppts, pv);

	}

}

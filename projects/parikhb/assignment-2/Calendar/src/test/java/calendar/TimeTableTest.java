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
	/*test get app range*/
	 @Test
	  public void test01()  throws Throwable  {
		 Calendar rightnow = Calendar.getInstance();
		 int thisMonth = rightnow.get(Calendar.MONTH+1);
		 int thisYear = rightnow.get(Calendar.YEAR);
		 int thisDay = rightnow.get(Calendar.DAY_OF_MONTH);
		 LinkedList<Appt> listAppts = new LinkedList<Appt>();

		 GregorianCalendar today = new GregorianCalendar(thisYear,thisMonth,thisDay);
		 GregorianCalendar tomorrow = (GregorianCalendar)today.clone();
		 tomorrow.add(Calendar.DAY_OF_MONTH,1);

		 int startHour=15;
		 int startMinute=30;
		 int startDay=thisDay+1;
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
		 assertEquals(1, t.getApptRange(listAppts, today, tomorrow).size());
	 }
/*test get app range error*/
	 @Test(expected = DateOutOfRangeException.class)
	  public void test02()  throws Throwable  {
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
	 /*test Get Next Appt Occurrence*/
	@Test
	public void test03()  throws Throwable  {

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

		GregorianCalendar today = new GregorianCalendar(thisYear,thisMonth,thisDay);
		GregorianCalendar tomorrow = (GregorianCalendar)today.clone();
		tomorrow.add(Calendar.MONTH,1);

		LinkedList<Appt> listAppts = new LinkedList<Appt>();
		listAppts.add(appt);

		TimeTable t = new TimeTable();
		GregorianCalendar day = new GregorianCalendar();
		assertEquals(28, t.getApptRange(listAppts, today, tomorrow).size());

	}
/*test Delete Appt*/
	@Test
	public void test04()  throws Throwable  {

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
	/* test Delete Appt Null */
	@Test
	public void test05()  throws Throwable  {

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
/*test permute*/
	@Test(expected = IllegalArgumentException.class)
	public void test06()  throws Throwable  {

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
		pv[1] = 2;
		LinkedList<Appt> permutedList = t.permute(listAppts, pv);
//		System.out.println(listAppts);
//		System.out.println(permutedList);
		assertEquals(listAppts.get(0), permutedList.get(0));

		int[] pv2 = new int[4];
		t.permute(listAppts, pv2);

	}

}
//add more unit tests as you needed

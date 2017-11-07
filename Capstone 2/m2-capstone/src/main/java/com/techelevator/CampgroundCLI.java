package com.techelevator;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;

import com.techelevator.projects.model.Campground;
import com.techelevator.projects.model.CampgroundDAO;
import com.techelevator.projects.model.CampsiteDAO;
import com.techelevator.projects.model.Park;
import com.techelevator.projects.model.ParkDAO;
import com.techelevator.projects.model.ReservationDAO;
import com.techelevator.projects.model.jdbc.JDBCCampgroundDAO;
import com.techelevator.projects.model.jdbc.JDBCCampsiteDAO;
import com.techelevator.projects.model.jdbc.JDBCParkDAO;
import com.techelevator.projects.model.jdbc.JDBCReservationDAO;
import com.techelevator.projects.view.Menu;

public class CampgroundCLI {
	private Park currentPark;
	private Campground currentCampground;
	
	public static void main(String[] args) {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setUrl("jdbc:postgresql://localhost:5432/campground");
		dataSource.setUsername("postgres");
		dataSource.setPassword("postgres1");
		
		CampgroundCLI application = new CampgroundCLI(dataSource);
		application.run();
	}
	private static int FIXED_WIDTH = 60;
	
	private static final String VIEW_PARK_ACADIA = "Acadia";
	private static final String VIEW_PARK_ARCHES = "Arches";
	private static final String VIEW_PARK_CUYAHOGA = "Cuyahoga National Valley Park";
	private static final String VIEW_PARK_EXIT = "Exit"; 
	private static final String[] VIEW_PARK_OPTIONS = new String[] { VIEW_PARK_ACADIA,
																	VIEW_PARK_ARCHES,
																	VIEW_PARK_CUYAHOGA,
																	VIEW_PARK_EXIT};
	
	private static final String PARK_INFORMATION_VIEW_CAMPGROUNDS = "View Campgrounds";
	private static final String PARK_INFORMATION_SEARCH_RESERVATIONS = "Search for Reservation";
	private static final String PARK_INFORMATION_RETURN_TO_PREVIOUS = "Return to Previous Screen";
	private static final String[] PARK_INFORMATION_OPTIONS = new String[] { PARK_INFORMATION_VIEW_CAMPGROUNDS,
									 									  PARK_INFORMATION_SEARCH_RESERVATIONS,
									 									  PARK_INFORMATION_RETURN_TO_PREVIOUS};
	
	private static final String CAMPGROUNDS_AVAILABLE_RESERVATION = "Search for Available Reservation";
	private static final String CAMPGROUNDS_RETURN_TO_PREVIOUS = "Return to Previous Screen";
	private static final String[] CAMPGROUNDS_OPTIONS = new String[] { CAMPGROUNDS_AVAILABLE_RESERVATION,
																	  CAMPGROUNDS_RETURN_TO_PREVIOUS };

	private static final String RESERVATION_WHICH_CAMPGROUND = "Which campground (enter 0 to cancel)? ";
	private static final String RESERVATION_ARRIVAL_DATE = "What is the arrival date? (__/__/____)";
	private static final String RESERVATION_DEPARTURE_DATE  = "What is the departure date? (__/__/____)";
	private static final String[] RESERVATION_MENU = new String[] { RESERVATION_WHICH_CAMPGROUND,
																 RESERVATION_ARRIVAL_DATE,
																 RESERVATION_DEPARTURE_DATE };

	
	private static final String PARKWIDE_RESERVATION_ARRIVAL_DATE = "What is the arrival date? (__/__/____)";
	private static final String PARKWIDE_RESERVATION_DEPARTURE_DATE = "What is the departure date? (__/__/____)";
	private static final String[] PARKWIDE_RESERVATION_DATES = new String[] { PARKWIDE_RESERVATION_ARRIVAL_DATE,
																			PARKWIDE_RESERVATION_DEPARTURE_DATE };
	
	private static final String RESERVATION_WHICH_SITE = "Which site should be reserved (enter 0 to cancel)?";
	private static final String RESERVATION_WHICH_NAME = "What name should the reservation be under?";
	private static final String[] PARKWIDE_RESERVATION_MAKE_RESERVATION_MENU = new String[] { RESERVATION_WHICH_SITE,
																							RESERVATION_WHICH_NAME };
	
	private static final String RESERVATION_CONFIRMATION = "The reservation has been made and the confirmation id is: ";
	
	private Menu menu;
	private CampgroundDAO campgroundDAO;
	private CampsiteDAO campsiteDAO;
	private ParkDAO parkDAO;
	private ReservationDAO reservationDAO;
	
	public CampgroundCLI(DataSource datasource) {
		this.menu = new Menu(System.in, System.out);
		
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setUrl("jdbc:postgresql://localhost:5432/campground");
		dataSource.setUsername("postgres");
		dataSource.setPassword("postgres1");
		
		campgroundDAO = new JDBCCampgroundDAO(dataSource);
		campsiteDAO = new JDBCCampsiteDAO(dataSource);
		parkDAO = new JDBCParkDAO(dataSource);
		reservationDAO = new JDBCReservationDAO(dataSource);
		
	}
	
	public void run() {
		while(true) {
			printHeading("View Parks Interface");
			System.out.println("Select a park for further details");
			String choice = (String)menu.getChoiceFromOptions(VIEW_PARK_OPTIONS);
			if(choice.equals(VIEW_PARK_ACADIA)) {
				currentPark = parkDAO.makePark("Acadia");
				handlePark();
			} else if (choice.equals(VIEW_PARK_ARCHES)) {
				currentPark = parkDAO.makePark("Arches");
				handlePark();
			}  else if (choice.equals(VIEW_PARK_CUYAHOGA)) {
				currentPark = parkDAO.makePark("Cuyahoga Valley");
				handlePark();
			} else if (choice.equals(VIEW_PARK_EXIT)) {
				System.exit(0);
			}
		}
	}
	
	private void handlePark() {
		printHeading("Park Information Screen");
		System.out.println(currentPark.getName() + "\n");
		StringBuilder sb = new StringBuilder(currentPark.getDescription());
		int i = 0;
		while ((i = sb.indexOf(" ", i + 65)) != -1) {
		    sb.replace(i, i + 1, "\n");
		}
		System.out.println(sb + "\n");
		System.out.println(String.format("%-18s%s", "Location:", currentPark.getLocation()));
		System.out.println(String.format("%-18s%s", "Established: ", currentPark.getEstablishDate()));
		System.out.println(String.format("%-18s%-7s%-3s%s", "Area: ", NumberFormat.getNumberInstance(Locale.US).format(currentPark.getArea()), "sq", "km"));
		System.out.println(String.format("%-18s%s", "Annual Visitors: ", NumberFormat.getNumberInstance(Locale.US).format(currentPark.getVisitors())) + "\n");
		String choice = (String)menu.getChoiceFromOptions(PARK_INFORMATION_OPTIONS);
		if(choice.equals(PARK_INFORMATION_VIEW_CAMPGROUNDS)) {
			handleCampground();
		} else if (choice.equals(PARK_INFORMATION_SEARCH_RESERVATIONS)) {
			//
		}  else if (choice.equals(PARK_INFORMATION_RETURN_TO_PREVIOUS)) {
		}
	}
	
	private void handleCampground() {
		printHeading("Park Campgrounds");
		List<Campground> allCampgrounds = campgroundDAO.getAllCampgrounds(currentPark);
		System.out.println(String.format("%s %s", currentPark.getName(), "National Park Campgrounds") + "\n");
		System.out.println(String.format("%-6s%-34s%-10s%-10s%s", "", "Name", "Open", "Close", "Daily Fee"));
		for (Campground camp : allCampgrounds) {
			System.out.println(String.format("#%-5d%-34s%-10s%-10s$%s0", camp.getCampgroundId(), camp.getName(), camp.getOpenFrom(), camp.getOpenTo(), camp.getDailyFee()));
		}

		
	}

	
	private void printHeading(String headingText) {
		System.out.println("\n"+headingText);
		for(int i = 0; i < headingText.length(); i++) {
			System.out.print("-");
		}
		System.out.println();
	}
}

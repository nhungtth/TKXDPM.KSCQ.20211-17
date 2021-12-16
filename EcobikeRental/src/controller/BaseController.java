package controller;

import entity.rentbike.RentBike;
import entity.user.User;

/**
 * This class is the base controller for our EcobikeRental project
 * 
 * @author NhungTTH
 */
public class BaseController {
	
	// get current bike rented
	public static RentBike getRentBike() {
		return RentBike.getCurrentBike();
	}
	
	// get current user
	public static User getUser() {
		return User.getCurrentUser();
	}
}

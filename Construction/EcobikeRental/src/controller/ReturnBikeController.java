package controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import entity.dock.Dock;
import utils.Configs;

/**
 * this class controls the flow of events in return bike usecase
 * @author NhungTTH
 *
 */
public class ReturnBikeController extends BaseController {

	/**
	 * this method checks if a dock is available now
	 * @param id: id of dock
	 * @return true if dock is available, else false
	 */
	public boolean checkAvailability(String id) {
		Dock dock = Dock.getDockById(id);
		if(dock != null)
			return dock.isStatus();
		else {
			return false;
		}
	}

	/**
	 * this method gets all docks which are available
	 * @return List[Dock]
	 */
	public List getAvailableDocks() {
		return Dock.getDocksAvailable();
	}

	/**
	 * this method calculates fees for renting a bike
	 * @param t: time renting bike (minutes)
	 * @return total: amount to pay
	 */
	public int calculateFees(long t) {
		int total = 0;
		if (t >= 10) {
			total += 10000;
			if (t > 30) {
				t = t - 30;
				total += (t / 15) * 3000;
			}
		}
		return total;
	}


}

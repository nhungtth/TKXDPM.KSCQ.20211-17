package controller;

import java.util.List;

import entity.dock.Dock;

public class ReturnBikeController extends BaseController{

	public boolean checkAvailability(String id) {
		return Dock.checkAvailable(id);
	}

	public List getAvailableDocks() {
		return Dock.getDocksAvailable();
	}

	public int calculateFees(long t) {
		
		return 0;
	}

	public int calculateDeposit(String id) {
		// TODO Auto-generated method stub
		return 0;
	}

}

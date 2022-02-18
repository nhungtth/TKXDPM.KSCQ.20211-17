package controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class CalculateDepositTest {
	private RentBikeController rentBikeController;

	@BeforeEach
	void setUp() throws Exception {
		this.rentBikeController = new RentBikeController();
	}

	@ParameterizedTest
	@CsvSource({
		"1000000, 400000",
		"1375000, 550000",
		"1750000, 700000",
	})
	void test(int price, int expected) {
		int deposit = this.rentBikeController.calculateDeposit(price);
		assertEquals(expected, deposit);
	}

}

package subsystem;

import common.exception.InternalServerErrorException;
import common.exception.InvalidCardException;
import common.exception.NotEnoughBalanceException;
import entity.transaction.CreditCard;
import entity.transaction.Transaction;
import subsystem.interbank.InterbankSubsystemController;

/***
 * The {@code InterbankSubsystem} class is used to communicate with the
 * Interbank to make transaction.
 * 
 * @author NhungTTH
 *
 */
public class InterbankSubsystem implements InterbankInterface {

	/**
	 * Represent the controller of the subsystem
	 */
	private InterbankSubsystemController ctrl;

	/**
	 * Initializes a newly created {@code InterbankSubsystem} object so that it
	 * represents an Interbank subsystem.
	 */
	public InterbankSubsystem() {
		String str = new String();
		this.ctrl = new InterbankSubsystemController();
	}

	/**
	 * @see InterbankInterface#processTransaction(entity.transaction.CreditCard, int,
	 *      java.lang.String, java.lang.String)
	 */
	public Transaction processTransaction(CreditCard card, int amount, String contents, String type) {
		Transaction transaction = ctrl.processTransaction(card, amount, contents, type);
		return transaction;
	}
}

package common.exception;;

/**
 * The StationUpdateException wraps all unchecked exceptions You can use this
 * exception to inform
 * 
 * @author nguyenlm
 */
public class StationUpdateException extends AimsException {

	private static final long serialVersionUID = 1091337136123906298L;

	public StationUpdateException() {

	}

	public StationUpdateException(String message) {
		super(message);
	}

}

package scrumcourse.exception;

@SuppressWarnings("serial")
public class DaoException extends BaseException {

	private DaoException(Throwable e) {
		super(e);
	}

	public static DaoException exception(Exception e) {
		if (e instanceof DaoException) {
			return (DaoException) e;
		} else {
			return new DaoException(e);
		}
	}
}

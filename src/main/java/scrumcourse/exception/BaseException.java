package scrumcourse.exception;

@SuppressWarnings("serial")
public abstract class BaseException extends RuntimeException {

	public BaseException(Throwable e) {
		super(e);
	}

}

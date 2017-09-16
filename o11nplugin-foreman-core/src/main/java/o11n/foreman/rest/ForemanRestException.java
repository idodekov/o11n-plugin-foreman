package o11n.foreman.rest;

public class ForemanRestException extends Exception {
	private static final long serialVersionUID = 1L;

	public ForemanRestException() {
		super();
	}

	public ForemanRestException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

	public ForemanRestException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public ForemanRestException(String arg0) {
		super(arg0);
	}

	public ForemanRestException(Throwable arg0) {
		super(arg0);
	}

}

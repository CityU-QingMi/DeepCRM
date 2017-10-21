	public Exception getException() {
		Throwable t = JspRuntimeLibrary.getThrowable(request);

		// Only wrap if needed
		if ((t != null) && (!(t instanceof Exception))) {
			t = new JspException(t);
		}

		return (Exception) t;
	}

	protected void printAsyncResult(MvcResult result) throws Exception {
		HttpServletRequest request = result.getRequest();
		this.printer.printValue("Async started", request.isAsyncStarted());
		Object asyncResult = null;
		try {
			asyncResult = result.getAsyncResult(0);
		}
		catch (IllegalStateException ex) {
			// Not set
		}
		this.printer.printValue("Async result", asyncResult);
	}

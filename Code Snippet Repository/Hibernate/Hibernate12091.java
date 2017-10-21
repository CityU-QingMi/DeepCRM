	private void invokeCallback(Method callback, Object target) {
		try {
			performCallbackInvocation( callback, target );
		}
		catch (CallbackException e) {
			// this is getting eaten, at least when run from IntelliJ.  The test fails to start (for start up
			// callbacks), but the exception is never shown..
			System.out.println( "Error performing callback invocation : " + e.getLocalizedMessage() );
			e.printStackTrace();
			throw e;
		}
	}

	private void assertFutureWithException(Future<Object> result,
			TestableAsyncUncaughtExceptionHandler exceptionHandler) {

		try {
			result.get();
		}
		catch (InterruptedException ex) {
			fail("Should not have failed with InterruptedException: " + ex);
		}
		catch (ExecutionException ex) {
			// expected
			assertEquals("Wrong exception cause", UnsupportedOperationException.class, ex.getCause().getClass());
		}
		assertFalse("handler should never be called with Future return type", exceptionHandler.isCalled());
	}

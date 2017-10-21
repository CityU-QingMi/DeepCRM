	@Test
	void failWithStringAndThrowable() {
		try {
			fail("message", new Throwable("cause"));
			expectAssertionFailedError();
		}
		catch (AssertionFailedError ex) {
			assertMessageEquals(ex, "message");
			Throwable cause = ex.getCause();
			assertMessageContains(cause, "cause");
		}
	}

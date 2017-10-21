	@Test
	void failWithThrowable() {
		try {
			fail((String) null, new Throwable("cause"));
			expectAssertionFailedError();
		}
		catch (AssertionFailedError ex) {
			assertMessageEquals(ex, "");
			Throwable cause = ex.getCause();
			assertMessageContains(cause, "cause");
		}
	}

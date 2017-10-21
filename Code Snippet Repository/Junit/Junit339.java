	@Test
	void failWithStringAndNullThrowable() {
		try {
			fail("message", (Throwable) null);
			expectAssertionFailedError();
		}
		catch (AssertionFailedError ex) {
			assertMessageEquals(ex, "message");
			if (ex.getCause() != null) {
				throw new AssertionError("Cause should have been null");
			}
		}
	}

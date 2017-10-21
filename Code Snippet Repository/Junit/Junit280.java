	@Test
	void assertIterableEqualsIterableVsNullAndMessage() {
		try {
			assertIterableEquals(null, listOf('a', "b", 10, 20D), "message");
			expectAssertionFailedError();
		}
		catch (AssertionFailedError ex) {
			assertMessageStartsWith(ex, "message");
			assertMessageEndsWith(ex, "expected iterable was <null>");
		}

		try {
			assertIterableEquals(listOf("hello", 42), null, "message");
			expectAssertionFailedError();
		}
		catch (AssertionFailedError ex) {
			assertMessageStartsWith(ex, "message");
			assertMessageEndsWith(ex, "actual iterable was <null>");
		}
	}

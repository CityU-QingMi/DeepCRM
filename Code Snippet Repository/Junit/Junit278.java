	@Test
	void assertIterableEqualsIterableVsNull() {
		try {
			assertIterableEquals(null, listOf("a", "b", 1, listOf()));
			expectAssertionFailedError();
		}
		catch (AssertionFailedError ex) {
			assertMessageEquals(ex, "expected iterable was <null>");
		}

		try {
			assertIterableEquals(listOf('a', 1, new Object(), 10L), null);
			expectAssertionFailedError();
		}
		catch (AssertionFailedError ex) {
			assertMessageEquals(ex, "actual iterable was <null>");
		}
	}

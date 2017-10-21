	@Test
	void assertIterableEqualsDifferentIterablesAndMessage() {
		try {
			assertIterableEquals(listOf(1.1D, 2L, "3"), listOf(1D, 2L, "3"), "message");
			expectAssertionFailedError();
		}
		catch (AssertionFailedError ex) {
			assertMessageStartsWith(ex, "message");
			assertMessageEndsWith(ex, "iterable contents differ at index [0], expected: <1.1> but was: <1.0>");
		}
	}

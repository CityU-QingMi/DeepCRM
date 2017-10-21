	@Test
	void assertIterableEqualsDifferentIterables() {
		try {
			assertIterableEquals(listOf(1L, "2", '3', 4, 5D), listOf(1L, "2", '9', 4, 5D));
			expectAssertionFailedError();
		}
		catch (AssertionFailedError ex) {
			assertMessageEquals(ex, "iterable contents differ at index [2], expected: <3> but was: <9>");
		}

		try {
			assertIterableEquals(listOf("a", 10, 11, 12, Double.NaN), listOf("a", 10, 11, 12, 13.55D));
			expectAssertionFailedError();
		}
		catch (AssertionFailedError ex) {
			assertMessageEquals(ex, "iterable contents differ at index [4], expected: <NaN> but was: <13.55>");
		}
	}

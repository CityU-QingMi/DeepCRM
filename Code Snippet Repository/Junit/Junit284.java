	@Test
	void assertIterableEqualsIterablesOfDifferentLength() {
		try {
			assertIterableEquals(listOf('a', "b", 'c'), listOf('a', "b", 'c', 1));
			expectAssertionFailedError();
		}
		catch (AssertionFailedError ex) {
			assertMessageEquals(ex, "iterable lengths differ, expected: <3> but was: <4>");
		}
	}

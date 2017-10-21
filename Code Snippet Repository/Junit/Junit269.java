	@Test
	void assertIterableEqualsNestedIterablesOfDifferentLengthAndMessage() {
		try {
			assertIterableEquals(listOf('a', 1, listOf(2, 3)), listOf('a', 1, listOf(2, 3, 4, 5)), "message");
			expectAssertionFailedError();
		}
		catch (AssertionFailedError ex) {
			assertMessageStartsWith(ex, "message");
			assertMessageEndsWith(ex, "iterable lengths differ at index [2], expected: <2> but was: <4>");
		}
	}

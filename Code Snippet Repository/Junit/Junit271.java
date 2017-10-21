	@Test
	void assertIterableEqualsNestedIterablesOfDifferentLengthAndMessageSupplier() {
		try {
			assertIterableEquals(listOf("a", setOf(1, 2, 3, listOf(4.0, 5.1, 6.1), 7)),
				listOf("a", setOf(1, 2, 3, listOf(4.0, 5.1, 6.1, 7.0), 8)), () -> "message");
			expectAssertionFailedError();
		}
		catch (AssertionFailedError ex) {
			assertMessageStartsWith(ex, "message");
			assertMessageEndsWith(ex, "iterable lengths differ at index [1][3], expected: <3> but was: <4>");
		}
	}

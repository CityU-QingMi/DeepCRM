	@Test
	void assertIterableEqualsIterablesOfDifferentLengthAndMessageSupplier() {
		try {
			assertIterableEquals(setOf("a", "b", "c"), setOf("a", "b", "c", "d", "e", "f"), () -> "message");
			expectAssertionFailedError();
		}
		catch (AssertionFailedError ex) {
			assertMessageStartsWith(ex, "message");
			assertMessageEndsWith(ex, "iterable lengths differ, expected: <3> but was: <6>");
		}
	}

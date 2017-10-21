	@Test
	void assertIterableEqualsDifferentIterablesAndMessageSupplier() {
		try {
			assertIterableEquals(setOf("one", 1L, Double.MIN_VALUE, "abc"), setOf("one", 1L, 42.42, "abc"),
				() -> "message");
			expectAssertionFailedError();
		}
		catch (AssertionFailedError ex) {
			assertMessageStartsWith(ex, "message");
			assertMessageEndsWith(ex, "iterable contents differ at index [2], expected: <4.9E-324> but was: <42.42>");
		}
	}

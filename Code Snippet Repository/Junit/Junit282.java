	@Test
	void assertIterableEqualsIterableVsNullAndMessageSupplier() {
		try {
			assertIterableEquals(null, setOf(42, "42", listOf(42F), 42D), () -> "message");
			expectAssertionFailedError();
		}
		catch (AssertionFailedError ex) {
			assertMessageStartsWith(ex, "message");
			assertMessageEndsWith(ex, "expected iterable was <null>");
		}

		try {
			assertIterableEquals(listOf(listOf("a"), listOf()), null, () -> "message");
			expectAssertionFailedError();
		}
		catch (AssertionFailedError ex) {
			assertMessageStartsWith(ex, "message");
			assertMessageEndsWith(ex, "actual iterable was <null>");
		}
	}

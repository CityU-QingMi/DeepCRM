	@Test
	void assertEqualsDoubleWithUnequalValuesAndMessageSupplier() {
		try {
			assertEquals(1.0d, 1.1d, () -> "message");
			expectAssertionFailedError();
		}
		catch (AssertionFailedError ex) {
			assertMessageStartsWith(ex, "message");
			assertMessageEndsWith(ex, "expected: <1.0> but was: <1.1>");
			assertExpectedAndActualValues(ex, 1.0d, 1.1d);
		}
	}

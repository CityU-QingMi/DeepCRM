	@Test
	void assertEqualsByteWithUnequalValuesAndMessageSupplier() {
		byte expected = 1;
		byte actual = 2;
		try {
			assertEquals(expected, actual, () -> "message");
			expectAssertionFailedError();
		}
		catch (AssertionFailedError ex) {
			assertMessageStartsWith(ex, "message");
			assertMessageEndsWith(ex, "expected: <1> but was: <2>");
			assertExpectedAndActualValues(ex, expected, actual);
		}
	}

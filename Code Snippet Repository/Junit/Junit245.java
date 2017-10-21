	@Test
	void assertEqualsDoubleWithUnequalValues() {
		try {
			assertEquals(1.0d, 1.1d);
			expectAssertionFailedError();
		}
		catch (AssertionFailedError ex) {
			assertMessageEquals(ex, "expected: <1.0> but was: <1.1>");
			assertExpectedAndActualValues(ex, 1.0d, 1.1d);
		}
	}

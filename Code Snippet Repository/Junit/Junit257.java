	@Test
	void assertEqualsShortWithUnequalValues() {
		short expected = 1;
		short actual = 2;
		try {
			assertEquals(expected, actual);
			expectAssertionFailedError();
		}
		catch (AssertionFailedError ex) {
			assertMessageEquals(ex, "expected: <1> but was: <2>");
			assertExpectedAndActualValues(ex, expected, actual);
		}
	}

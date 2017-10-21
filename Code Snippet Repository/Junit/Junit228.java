	@Test
	void assertEqualsByteWithUnequalValues() {
		byte expected = 1;
		byte actual = 2;
		try {
			assertEquals(expected, actual);
			expectAssertionFailedError();
		}
		catch (AssertionFailedError ex) {
			assertMessageEquals(ex, "expected: <1> but was: <2>");
			assertExpectedAndActualValues(ex, expected, actual);
		}
	}

	@Test
	void assertEqualsIntWithUnequalValues() {
		try {
			assertEquals(1, 2);
			expectAssertionFailedError();
		}
		catch (AssertionFailedError ex) {
			assertMessageEquals(ex, "expected: <1> but was: <2>");
			assertExpectedAndActualValues(ex, 1, 2);
		}
	}

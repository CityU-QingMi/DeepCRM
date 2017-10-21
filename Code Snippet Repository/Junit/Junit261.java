	@Test
	void assertEqualsIntWithUnequalValuesAndMessage() {
		try {
			assertEquals(1, 2, "message");
			expectAssertionFailedError();
		}
		catch (AssertionFailedError ex) {
			assertMessageStartsWith(ex, "message");
			assertMessageEndsWith(ex, "expected: <1> but was: <2>");
			assertExpectedAndActualValues(ex, 1, 2);
		}
	}

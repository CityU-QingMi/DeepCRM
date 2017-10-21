	@Test
	void assertEqualsLongWithUnequalValuesAndMessage() {
		try {
			assertEquals(1L, 2L, "message");
			expectAssertionFailedError();
		}
		catch (AssertionFailedError ex) {
			assertMessageStartsWith(ex, "message");
			assertMessageEndsWith(ex, "expected: <1> but was: <2>");
			assertExpectedAndActualValues(ex, 1L, 2L);
		}
	}

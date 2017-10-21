	@Test
	void assertEqualsLongWithUnequalValues() {
		try {
			assertEquals(1L, 2L);
			expectAssertionFailedError();
		}
		catch (AssertionFailedError ex) {
			assertMessageEquals(ex, "expected: <1> but was: <2>");
			assertExpectedAndActualValues(ex, 1L, 2L);
		}
	}

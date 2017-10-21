	@Test
	void assertEqualsFloatWithUnequalValues() {
		try {
			assertEquals(1.0f, 1.1f);
			expectAssertionFailedError();
		}
		catch (AssertionFailedError ex) {
			assertMessageEquals(ex, "expected: <1.0> but was: <1.1>");
			assertExpectedAndActualValues(ex, 1.0f, 1.1f);
		}
	}

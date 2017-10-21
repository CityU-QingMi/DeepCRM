	@Test
	void assertEqualsFloatWithUnequalValuesAndMessage() {
		try {
			assertEquals(1.0f, 1.1f, "message");
			expectAssertionFailedError();
		}
		catch (AssertionFailedError ex) {
			assertMessageStartsWith(ex, "message");
			assertMessageEndsWith(ex, "expected: <1.0> but was: <1.1>");
			assertExpectedAndActualValues(ex, 1.0f, 1.1f);
		}
	}

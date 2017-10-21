	@Test
	void assertEqualsCharWithUnequalValues() {
		try {
			assertEquals('a', 'b');
			expectAssertionFailedError();
		}
		catch (AssertionFailedError ex) {
			assertMessageEquals(ex, "expected: <a> but was: <b>");
			assertExpectedAndActualValues(ex, 'a', 'b');
		}
	}

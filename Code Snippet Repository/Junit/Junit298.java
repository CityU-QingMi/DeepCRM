	@Test
	void assertNotSameWithTwoNulls() {
		try {
			assertNotSame(null, null);
			expectAssertionFailedError();
		}
		catch (AssertionFailedError ex) {
			assertMessageEquals(ex, "expected: not same but was: <null>");
		}
	}

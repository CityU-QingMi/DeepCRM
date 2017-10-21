	@Test
	void assertSameWithObjectVsNull() {
		Object expected = new Object();
		try {
			assertSame(expected, null);
			expectAssertionFailedError();
		}
		catch (AssertionFailedError ex) {
			assertMessageContains(ex, "expected: <java.lang.Object@");
			assertMessageContains(ex, "but was: <null>");
			assertExpectedAndActualValues(ex, expected, null);
		}
	}

	@Test
	void assertSameWithNullVsObject() {
		Object actual = new Object();
		try {
			assertSame(null, actual);
			expectAssertionFailedError();
		}
		catch (AssertionFailedError ex) {
			assertMessageContains(ex, "expected: <null>");
			assertMessageContains(ex, "but was: <java.lang.Object@");
			assertExpectedAndActualValues(ex, null, actual);
		}
	}

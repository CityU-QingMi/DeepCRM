	@Test
	void assertSameWithDifferentObjects() {
		Object expected = new Object();
		Object actual = new Object();
		try {
			assertSame(expected, actual);
			expectAssertionFailedError();
		}
		catch (AssertionFailedError ex) {
			assertMessageContains(ex, "expected: <java.lang.Object@");
			assertMessageContains(ex, "but was: <java.lang.Object@");
			assertExpectedAndActualValues(ex, expected, actual);
		}
	}

	@Test
	void assertSameWithEquivalentStringsAndMessageSupplier() {
		String expected = new String("foo");
		String actual = new String("foo");
		try {
			assertSame(expected, actual, () -> "test");
			expectAssertionFailedError();
		}
		catch (AssertionFailedError ex) {
			assertMessageStartsWith(ex, "test");
			assertMessageContains(ex, "expected: java.lang.String@");
			assertMessageContains(ex, "but was: java.lang.String@");
			assertExpectedAndActualValues(ex, expected, actual);
		}
	}

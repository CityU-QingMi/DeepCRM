	@Test
	void assertArrayEqualsCharArraysOfDifferentLengthAndMessageSupplier() {
		try {
			assertArrayEquals(new char[] { 'q' }, new char[] { 't', 'u' }, () -> "message");
			expectAssertionFailedError();
		}
		catch (AssertionFailedError ex) {
			assertMessageStartsWith(ex, "message");
			assertMessageEndsWith(ex, "array lengths differ, expected: <1> but was: <2>");
		}
	}

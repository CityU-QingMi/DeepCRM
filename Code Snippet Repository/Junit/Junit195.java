	@Test
	void assertArrayEqualsBooleanArraysOfDifferentLengthAndMessageSupplier() {
		try {
			assertArrayEquals(new boolean[] { true }, new boolean[] { true, false }, () -> "message");
			expectAssertionFailedError();
		}
		catch (AssertionFailedError ex) {
			assertMessageStartsWith(ex, "message");
			assertMessageEndsWith(ex, "array lengths differ, expected: <1> but was: <2>");
		}
	}

	@Test
	void assertArrayEqualsDoubleArraysOfDifferentLengthAndMessageSupplier() {
		try {
			assertArrayEquals(new double[] { 1.15D, 2.2, 2.3 }, new double[] { 1.15D, 1.15D }, () -> "message");
			expectAssertionFailedError();
		}
		catch (AssertionFailedError ex) {
			assertMessageStartsWith(ex, "message");
			assertMessageEndsWith(ex, "array lengths differ, expected: <3> but was: <2>");
		}
	}

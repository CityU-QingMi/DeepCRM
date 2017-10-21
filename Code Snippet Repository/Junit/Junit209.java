	@Test
	void assertArrayEqualsDoubleArraysOfDifferentLengthAndMessage() {
		try {
			assertArrayEquals(new double[] { 11.1, 99.1, 2 }, new double[] { .9, .1, .0, .1, .3 }, "message");
			expectAssertionFailedError();
		}
		catch (AssertionFailedError ex) {
			assertMessageStartsWith(ex, "message");
			assertMessageEndsWith(ex, "array lengths differ, expected: <3> but was: <5>");
		}
	}

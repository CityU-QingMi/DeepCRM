	@Test
	void assertArrayEqualsDeltaDoubleArraysOfDifferentLengthAndMessage() {
		try {
			assertArrayEquals(new double[] { 1.1, 99.1, 3.1 }, new double[] { .9, .1, .0, .1, .3 }, 0.1, "message");
			expectAssertionFailedError();
		}
		catch (AssertionFailedError ex) {
			assertMessageStartsWith(ex, "message");
			assertMessageEndsWith(ex, "array lengths differ, expected: <3> but was: <5>");
		}
	}

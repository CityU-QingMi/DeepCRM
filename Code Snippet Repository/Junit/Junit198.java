	@Test
	void assertArrayEqualsDeltaFloatArraysOfDifferentLengthAndMessage() {
		try {
			assertArrayEquals(new float[] { 19.1F, 12.77F }, new float[] { .9F, .8F, 5.123F }, 0.1F, "message");
			expectAssertionFailedError();
		}
		catch (AssertionFailedError ex) {
			assertMessageStartsWith(ex, "message");
			assertMessageEndsWith(ex, "array lengths differ, expected: <2> but was: <3>");
		}
	}

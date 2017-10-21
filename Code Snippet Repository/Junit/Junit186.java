	@Test
	void assertArrayEqualsFloatArraysOfDifferentLengthAndMessage() {
		try {
			assertArrayEquals(new float[] { 19.1F, 12.77F, 18.F }, new float[] { .9F, .8F, 5.123F, .10F }, "message");
			expectAssertionFailedError();
		}
		catch (AssertionFailedError ex) {
			assertMessageStartsWith(ex, "message");
			assertMessageEndsWith(ex, "array lengths differ, expected: <3> but was: <4>");
		}
	}

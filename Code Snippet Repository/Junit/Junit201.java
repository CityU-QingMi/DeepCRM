	@Test
	void assertArrayEqualsDeltaDifferentFloatArraysAndMessage() {
		try {
			assertArrayEquals(new float[] { 1.91F, 0.5F, .4F, 0.3F }, new float[] { 2F, 0.509F, .499F, -0.333F }, 0.1F,
				"message");
			expectAssertionFailedError();
		}
		catch (AssertionFailedError ex) {
			assertMessageStartsWith(ex, "message");
			assertMessageEndsWith(ex, "array contents differ at index [3], expected: <0.3> but was: <-0.333>");
		}
	}

	@Test
	void assertArrayEqualsDifferentFloatArraysAndMessage() {
		try {
			assertArrayEquals(new float[] { 1.9F, 0.5F, 0.4F, 0.3F }, new float[] { 1.9F, 0.5F, 0.4F, -0.333F },
				"message");
			expectAssertionFailedError();
		}
		catch (AssertionFailedError ex) {
			assertMessageStartsWith(ex, "message");
			assertMessageEndsWith(ex, "array contents differ at index [3], expected: <0.3> but was: <-0.333>");
		}
	}

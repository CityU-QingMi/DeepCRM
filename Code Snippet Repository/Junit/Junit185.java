	@Test
	void assertArrayEqualsFloatArraysOfDifferentLength() {
		try {
			assertArrayEquals(new float[] { Float.MIN_VALUE, 1F, 2F, 3F }, new float[] { Float.MAX_VALUE, 7.1F });
			expectAssertionFailedError();
		}
		catch (AssertionFailedError ex) {
			assertMessageEquals(ex, "array lengths differ, expected: <4> but was: <2>");
		}
	}

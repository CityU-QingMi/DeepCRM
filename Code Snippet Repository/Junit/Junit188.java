	@Test
	void assertArrayEqualsDifferentFloatArrays() {
		try {
			assertArrayEquals(new float[] { 5.5F, 6.5F, 7.5F, 8.5F }, new float[] { 5.5F, 6.5F, 7.4F, 8.5F });
			expectAssertionFailedError();
		}
		catch (AssertionFailedError ex) {
			assertMessageEquals(ex, "array contents differ at index [2], expected: <7.5> but was: <7.4>");
		}

		try {
			assertArrayEquals(new float[] { 1.0F, 2.0F, 3.0F, Float.NaN }, new float[] { 1.0F, 2.0F, 3.0F, 4.0F });
			expectAssertionFailedError();
		}
		catch (AssertionFailedError ex) {
			assertMessageEquals(ex, "array contents differ at index [3], expected: <NaN> but was: <4.0>");
		}
	}

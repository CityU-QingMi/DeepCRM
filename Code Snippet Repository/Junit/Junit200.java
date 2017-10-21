	@Test
	void assertArrayEqualsDeltaDifferentFloatArrays() {
		try {
			assertArrayEquals(new float[] { 5.6F, 3.2F, 9.1F, 0.5F }, new float[] { 5.55F, 3.3F, 9.201F, 0.51F }, 0.1F);
			expectAssertionFailedError();
		}
		catch (AssertionFailedError ex) {
			assertMessageEquals(ex, "array contents differ at index [2], expected: <9.1> but was: <9.201>");
		}

		try {
			assertArrayEquals(new float[] { 1.0F, 2.0F, 3.0F, Float.NaN }, new float[] { 1.5F, 1.5F, 2.9F, 4.0F },
				0.5F);
			expectAssertionFailedError();
		}
		catch (AssertionFailedError ex) {
			assertMessageEquals(ex, "array contents differ at index [3], expected: <NaN> but was: <4.0>");
		}
	}

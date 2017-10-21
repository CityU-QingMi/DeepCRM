	@Test
	void assertArrayEqualsDeltaDifferentFloatArraysAndMessageSupplier() {
		try {
			assertArrayEquals(new float[] { 0.3F, 0.9F, 8F }, new float[] { 0.6F, Float.MIN_VALUE, 8.4F }, 0.5F,
				() -> "message");
			expectAssertionFailedError();
		}
		catch (AssertionFailedError ex) {
			assertMessageStartsWith(ex, "message");
			assertMessageEndsWith(ex, "array contents differ at index [1], expected: <0.9> but was: <1.4E-45>");
		}
	}

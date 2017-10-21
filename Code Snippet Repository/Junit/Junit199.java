	@Test
	void assertArrayEqualsDeltaFloatArraysOfDifferentLengthAndMessageSupplier() {
		try {
			assertArrayEquals(new float[] { 1.1F, 1.2F, 1.3F }, new float[] { 1F, 2F, 3F, 4F }, 0.1F, () -> "message");
			expectAssertionFailedError();
		}
		catch (AssertionFailedError ex) {
			assertMessageStartsWith(ex, "message");
			assertMessageEndsWith(ex, "array lengths differ, expected: <3> but was: <4>");
		}
	}

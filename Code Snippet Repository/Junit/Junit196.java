	@Test
	void assertArrayEqualsDeltaFloatArrayVsNullAndMessageSupplier() {
		try {
			assertArrayEquals(null, new float[] { 5F, 6F, 7.77F, 8.88F }, 0.1F, () -> "message");
			expectAssertionFailedError();
		}
		catch (AssertionFailedError ex) {
			assertMessageStartsWith(ex, "message");
			assertMessageEndsWith(ex, "expected array was <null>");
		}

		try {
			assertArrayEquals(new float[] { 1F, 1.1F, 1.11F, 1.111F }, null, 0.1F, () -> "message");
			expectAssertionFailedError();
		}
		catch (AssertionFailedError ex) {
			assertMessageStartsWith(ex, "message");
			assertMessageEndsWith(ex, "actual array was <null>");
		}
	}

	@Test
	void assertArrayEqualsDeltaDoubleArrayVsNullAndMessageSupplier() {
		try {
			assertArrayEquals(null, new double[] { 1.2, 1.3, 1.4, 2.2002 }, 1, () -> "message");
			expectAssertionFailedError();
		}
		catch (AssertionFailedError ex) {
			assertMessageStartsWith(ex, "message");
			assertMessageEndsWith(ex, "expected array was <null>");
		}

		try {
			assertArrayEquals(new double[] { 13.13, 43.33, 100 }, null, 1.5, () -> "message");
			expectAssertionFailedError();
		}
		catch (AssertionFailedError ex) {
			assertMessageStartsWith(ex, "message");
			assertMessageEndsWith(ex, "actual array was <null>");
		}
	}

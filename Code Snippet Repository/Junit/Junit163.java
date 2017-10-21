	@Test
	void assertArrayEqualsIntArrayVsNullAndMessageSupplier() {
		try {
			assertArrayEquals(null, new int[] { 1, 10, 100, 1000, 10000, 100000 }, () -> "message");
			expectAssertionFailedError();
		}
		catch (AssertionFailedError ex) {
			assertMessageStartsWith(ex, "message");
			assertMessageEndsWith(ex, "expected array was <null>");
		}

		try {
			assertArrayEquals(new int[] { 100000, 10000, 1000, 100, 10, 1 }, null, () -> "message");
			expectAssertionFailedError();
		}
		catch (AssertionFailedError ex) {
			assertMessageStartsWith(ex, "message");
			assertMessageEndsWith(ex, "actual array was <null>");
		}
	}

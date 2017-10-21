	@Test
	void assertArrayEqualsLongArrayVsNullAndMessageSupplier() {
		try {
			assertArrayEquals(null, new long[] { 12345678910L, 10, 9, 8 }, () -> "message");
			expectAssertionFailedError();
		}
		catch (AssertionFailedError ex) {
			assertMessageStartsWith(ex, "message");
			assertMessageEndsWith(ex, "expected array was <null>");
		}

		try {
			assertArrayEquals(new long[] { 8, 9, 10, 12345678910L }, null, () -> "message");
			expectAssertionFailedError();
		}
		catch (AssertionFailedError ex) {
			assertMessageStartsWith(ex, "message");
			assertMessageEndsWith(ex, "actual array was <null>");
		}
	}

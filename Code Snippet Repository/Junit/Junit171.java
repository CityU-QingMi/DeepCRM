	@Test
	void assertArrayEqualsLongArrayVsNullAndMessage() {
		try {
			assertArrayEquals(null, new long[] { 42, 4242, 424242, 4242424242L }, "message");
			expectAssertionFailedError();
		}
		catch (AssertionFailedError ex) {
			assertMessageStartsWith(ex, "message");
			assertMessageEndsWith(ex, "expected array was <null>");
		}

		try {
			assertArrayEquals(new long[] { 4242424242L, 424242, 4242, 42 }, null, "message");
			expectAssertionFailedError();
		}
		catch (AssertionFailedError ex) {
			assertMessageStartsWith(ex, "message");
			assertMessageEndsWith(ex, "actual array was <null>");
		}
	}

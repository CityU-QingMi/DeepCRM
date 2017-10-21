	@Test
	void assertArrayEqualsIntArrayVsNullAndMessage() {
		try {
			assertArrayEquals(null, new int[] { 99_999, 88_888, 1 }, "message");
			expectAssertionFailedError();
		}
		catch (AssertionFailedError ex) {
			assertMessageStartsWith(ex, "message");
			assertMessageEndsWith(ex, "expected array was <null>");
		}

		try {
			assertArrayEquals(new int[] { 99_999, 77_7777, 2 }, null, "message");
			expectAssertionFailedError();
		}
		catch (AssertionFailedError ex) {
			assertMessageStartsWith(ex, "message");
			assertMessageEndsWith(ex, "actual array was <null>");
		}
	}

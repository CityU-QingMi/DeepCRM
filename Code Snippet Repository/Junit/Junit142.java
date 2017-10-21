	@Test
	void assertArrayEqualsByteArrayVsNullAndMessage() {
		try {
			assertArrayEquals(null, new byte[] { 9, 8, 7 }, "message");
			expectAssertionFailedError();
		}
		catch (AssertionFailedError ex) {
			assertMessageStartsWith(ex, "message");
			assertMessageEndsWith(ex, "expected array was <null>");
		}

		try {
			assertArrayEquals(new byte[] { 9, 8, 7 }, null, "message");
			expectAssertionFailedError();
		}
		catch (AssertionFailedError ex) {
			assertMessageStartsWith(ex, "message");
			assertMessageEndsWith(ex, "actual array was <null>");
		}
	}

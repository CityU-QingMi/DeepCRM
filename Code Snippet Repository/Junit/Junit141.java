	@Test
	void assertArrayEqualsByteArrayVsNull() {
		try {
			assertArrayEquals(null, new byte[] { 7, 8, 9 });
			expectAssertionFailedError();
		}
		catch (AssertionFailedError ex) {
			assertMessageEquals(ex, "expected array was <null>");
		}

		try {
			assertArrayEquals(new byte[] { 7, 8, 9 }, null);
			expectAssertionFailedError();
		}
		catch (AssertionFailedError ex) {
			assertMessageEquals(ex, "actual array was <null>");
		}
	}

	@Test
	void assertArrayEqualsLongArrayVsNull() {
		try {
			assertArrayEquals(null, new long[] { Long.MAX_VALUE, 2, 10 });
			expectAssertionFailedError();
		}
		catch (AssertionFailedError ex) {
			assertMessageEquals(ex, "expected array was <null>");
		}

		try {
			assertArrayEquals(new long[] { Long.MAX_VALUE, 2, 10 }, null);
			expectAssertionFailedError();
		}
		catch (AssertionFailedError ex) {
			assertMessageEquals(ex, "actual array was <null>");
		}
	}

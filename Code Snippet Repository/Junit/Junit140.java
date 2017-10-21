	@Test
	void assertArrayEqualsBooleanArrayVsNull() {
		try {
			assertArrayEquals(null, new boolean[] { true, false });
			expectAssertionFailedError();
		}
		catch (AssertionFailedError ex) {
			assertMessageEquals(ex, "expected array was <null>");
		}

		try {
			assertArrayEquals(new boolean[] { true, false }, null);
			expectAssertionFailedError();
		}
		catch (AssertionFailedError ex) {
			assertMessageEquals(ex, "actual array was <null>");
		}
	}

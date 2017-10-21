	@Test
	void assertArrayEqualsShortArrayVsNull() {
		try {
			assertArrayEquals(null, new short[] { 5, 10, 12 });
			expectAssertionFailedError();
		}
		catch (AssertionFailedError ex) {
			assertMessageEquals(ex, "expected array was <null>");
		}

		try {
			assertArrayEquals(new short[] { 5, 10, 12 }, null);
			expectAssertionFailedError();
		}
		catch (AssertionFailedError ex) {
			assertMessageEquals(ex, "actual array was <null>");
		}
	}

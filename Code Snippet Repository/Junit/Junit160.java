	@Test
	void assertArrayEqualsIntArrayVsNull() {
		try {
			assertArrayEquals(null, new int[] { Integer.MIN_VALUE, 2, 10 });
			expectAssertionFailedError();
		}
		catch (AssertionFailedError ex) {
			assertMessageEquals(ex, "expected array was <null>");
		}

		try {
			assertArrayEquals(new int[] { Integer.MIN_VALUE, 2, 10 }, null);
			expectAssertionFailedError();
		}
		catch (AssertionFailedError ex) {
			assertMessageEquals(ex, "actual array was <null>");
		}
	}

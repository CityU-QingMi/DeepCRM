	@Test
	void assertArrayEqualsCharArrayVsNull() {
		try {
			assertArrayEquals(null, new char[] { 'a', 'z' });
			expectAssertionFailedError();
		}
		catch (AssertionFailedError ex) {
			assertMessageEquals(ex, "expected array was <null>");
		}

		try {
			assertArrayEquals(new char[] { 'a', 'z' }, null);
			expectAssertionFailedError();
		}
		catch (AssertionFailedError ex) {
			assertMessageEquals(ex, "actual array was <null>");
		}
	}

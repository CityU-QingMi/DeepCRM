	@Test
	void assertArrayEqualsCharArrayVsNullAndMessage() {
		try {
			assertArrayEquals(null, new char[] { 'a', 'b', 'z' }, "message");
			expectAssertionFailedError();
		}
		catch (AssertionFailedError ex) {
			assertMessageStartsWith(ex, "message");
			assertMessageEndsWith(ex, "expected array was <null>");
		}

		try {
			assertArrayEquals(new char[] { 'a', 'b', 'z' }, null, "message");
			expectAssertionFailedError();
		}
		catch (AssertionFailedError ex) {
			assertMessageStartsWith(ex, "message");
			assertMessageEndsWith(ex, "actual array was <null>");
		}
	}

	@Test
	void assertArrayEqualsFloatArrayVsNullAndMessage() {
		try {
			assertArrayEquals(null, new float[] { 42.42F, 42.4242F, 19.20F }, "message");
			expectAssertionFailedError();
		}
		catch (AssertionFailedError ex) {
			assertMessageStartsWith(ex, "message");
			assertMessageEndsWith(ex, "expected array was <null>");
		}

		try {
			assertArrayEquals(new float[] { 11.101F, 12.101F, 99.9F }, null, "message");
			expectAssertionFailedError();
		}
		catch (AssertionFailedError ex) {
			assertMessageStartsWith(ex, "message");
			assertMessageEndsWith(ex, "actual array was <null>");
		}
	}

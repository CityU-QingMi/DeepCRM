	@Test
	void assertArrayEqualsFloatArrayVsNull() {
		try {
			assertArrayEquals(null, new float[] { Float.MAX_VALUE, 4.2F, 9.0F });
			expectAssertionFailedError();
		}
		catch (AssertionFailedError ex) {
			assertMessageEquals(ex, "expected array was <null>");
		}

		try {
			assertArrayEquals(new float[] { Float.MIN_VALUE, 2.3F, 10.10F }, null);
			expectAssertionFailedError();
		}
		catch (AssertionFailedError ex) {
			assertMessageEquals(ex, "actual array was <null>");
		}
	}

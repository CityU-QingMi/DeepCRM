	@Test
	void assertArrayEqualsObjectArrayVsNull() {
		try {
			assertArrayEquals(null, new Object[] { "a", "b", 1, new Object() });
			expectAssertionFailedError();
		}
		catch (AssertionFailedError ex) {
			assertMessageEquals(ex, "expected array was <null>");
		}

		try {
			assertArrayEquals(new Object[] { 'a', 1, new Object(), 10L }, null);
			expectAssertionFailedError();
		}
		catch (AssertionFailedError ex) {
			assertMessageEquals(ex, "actual array was <null>");
		}
	}

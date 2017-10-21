	@Test
	void assertArrayEqualsCharArrayVsNullAndMessageSupplier() {
		try {
			assertArrayEquals(null, new char[] { 'z', 'x', 'y' }, () -> "message");
			expectAssertionFailedError();
		}
		catch (AssertionFailedError ex) {
			assertMessageStartsWith(ex, "message");
			assertMessageEndsWith(ex, "expected array was <null>");
		}

		try {
			assertArrayEquals(new char[] { 'z', 'x', 'y' }, null, () -> "message");
			expectAssertionFailedError();
		}
		catch (AssertionFailedError ex) {
			assertMessageStartsWith(ex, "message");
			assertMessageEndsWith(ex, "actual array was <null>");
		}
	}

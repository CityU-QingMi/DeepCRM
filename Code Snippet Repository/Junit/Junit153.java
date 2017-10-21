	@Test
	void assertArrayEqualsShortArrayVsNullAndMessageSupplier() {
		try {
			assertArrayEquals(null, new short[] { 1, 2, 3, 4 }, () -> "message");
			expectAssertionFailedError();
		}
		catch (AssertionFailedError ex) {
			assertMessageStartsWith(ex, "message");
			assertMessageEndsWith(ex, "expected array was <null>");
		}

		try {
			assertArrayEquals(new short[] { -2000, 1, 2, 3, 4 }, null, () -> "message");
			expectAssertionFailedError();
		}
		catch (AssertionFailedError ex) {
			assertMessageStartsWith(ex, "message");
			assertMessageEndsWith(ex, "actual array was <null>");
		}
	}

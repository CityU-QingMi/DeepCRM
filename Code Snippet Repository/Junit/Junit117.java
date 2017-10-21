	@Test
	void assertArrayEqualsObjectArrayVsNullAndMessageSupplier() {
		try {
			assertArrayEquals(null, new Object[] { 42, "42", new float[] { 42F }, 42D }, () -> "message");
			expectAssertionFailedError();
		}
		catch (AssertionFailedError ex) {
			assertMessageStartsWith(ex, "message");
			assertMessageEndsWith(ex, "expected array was <null>");
		}

		try {
			assertArrayEquals(new Object[] { new Object[] { "a" }, new Object() }, null, () -> "message");
			expectAssertionFailedError();
		}
		catch (AssertionFailedError ex) {
			assertMessageStartsWith(ex, "message");
			assertMessageEndsWith(ex, "actual array was <null>");
		}
	}

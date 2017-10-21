	@Test
	void assertArrayEqualsByteArraysOfDifferentLengthAndMessageSupplier() {
		try {
			assertArrayEquals(new byte[] { 88, 99 }, new byte[] { 99, 88, 77 }, () -> "message");
			expectAssertionFailedError();
		}
		catch (AssertionFailedError ex) {
			assertMessageStartsWith(ex, "message");
			assertMessageEndsWith(ex, "array lengths differ, expected: <2> but was: <3>");
		}
	}

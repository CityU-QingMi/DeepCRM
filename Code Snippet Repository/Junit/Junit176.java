	@Test
	void assertArrayEqualsLongArraysOfDifferentLengthAndMessageSupplier() {
		try {
			assertArrayEquals(new long[] { Long.MAX_VALUE, Long.MIN_VALUE }, new long[] { 1L, 2L, 42L },
				() -> "message");
			expectAssertionFailedError();
		}
		catch (AssertionFailedError ex) {
			assertMessageStartsWith(ex, "message");
			assertMessageEndsWith(ex, "array lengths differ, expected: <2> but was: <3>");
		}
	}

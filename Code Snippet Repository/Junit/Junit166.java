	@Test
	void assertArrayEqualsIntArraysOfDifferentLengthAndMessageSupplier() {
		try {
			assertArrayEquals(new int[] { Integer.MAX_VALUE, Integer.MIN_VALUE }, new int[] { 1, 2, 3 },
				() -> "message");
			expectAssertionFailedError();
		}
		catch (AssertionFailedError ex) {
			assertMessageStartsWith(ex, "message");
			assertMessageEndsWith(ex, "array lengths differ, expected: <2> but was: <3>");
		}
	}

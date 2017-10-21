	@Test
	void assertArrayEqualsDifferentIntArraysAndMessageSupplier() {
		try {
			assertArrayEquals(new int[] { 1, Integer.MIN_VALUE, 2 }, new int[] { 1, Integer.MAX_VALUE, 2 },
				() -> "message");
			expectAssertionFailedError();
		}
		catch (AssertionFailedError ex) {
			assertMessageStartsWith(ex, "message");
			assertMessageEndsWith(ex,
				"array contents differ at index [1], expected: <-2147483648> but was: <2147483647>");
		}
	}

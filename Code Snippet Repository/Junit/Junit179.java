	@Test
	void assertArrayEqualsDifferentLongArraysAndMessageSupplier() {
		try {
			assertArrayEquals(new long[] { 42, -9999L, 2 }, new long[] { 42L, +9999L, 2L }, () -> "message");
			expectAssertionFailedError();
		}
		catch (AssertionFailedError ex) {
			assertMessageStartsWith(ex, "message");
			assertMessageEndsWith(ex, "array contents differ at index [1], expected: <-9999> but was: <9999>");
		}
	}

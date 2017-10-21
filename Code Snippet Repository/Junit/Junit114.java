	@Test
	void assertArrayEqualsDifferentBooleanArraysAndMessageSupplier() {
		try {
			assertArrayEquals(new boolean[] { false, false, false }, new boolean[] { false, true, true },
				() -> "message");
			expectAssertionFailedError();
		}
		catch (AssertionFailedError ex) {
			assertMessageStartsWith(ex, "message");
			assertMessageEndsWith(ex, "array contents differ at index [1], expected: <false> but was: <true>");
		}
	}

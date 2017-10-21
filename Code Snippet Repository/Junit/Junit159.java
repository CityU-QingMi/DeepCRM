	@Test
	void assertArrayEqualsDifferentShortArraysAndMessageSupplier() {
		try {
			assertArrayEquals(new short[] { 1000, 2000, +3000, 42 }, new short[] { 1000, 2000, -3000, 42 },
				() -> "message");
			expectAssertionFailedError();
		}
		catch (AssertionFailedError ex) {
			assertMessageStartsWith(ex, "message");
			assertMessageEndsWith(ex, "array contents differ at index [2], expected: <3000> but was: <-3000>");
		}
	}

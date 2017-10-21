	@Test
	void assertArrayEqualsDifferentByteArraysAndMessageSupplier() {
		try {
			assertArrayEquals(new byte[] { 127, 126, -128, +127 }, new byte[] { 127, 126, -128, -127 },
				() -> "message");
			expectAssertionFailedError();
		}
		catch (AssertionFailedError ex) {
			assertMessageStartsWith(ex, "message");
			assertMessageEndsWith(ex, "array contents differ at index [3], expected: <127> but was: <-127>");
		}
	}

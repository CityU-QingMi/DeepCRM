	@Test
	void assertArrayEqualsDifferentIntArraysAndMessage() {
		try {
			assertArrayEquals(new int[] { 9, 10, 100, 100_000, 7 }, new int[] { 9, 10, 100, 100_000, 200_000 },
				"message");
			expectAssertionFailedError();
		}
		catch (AssertionFailedError ex) {
			assertMessageStartsWith(ex, "message");
			assertMessageEndsWith(ex, "array contents differ at index [4], expected: <7> but was: <200000>");
		}
	}

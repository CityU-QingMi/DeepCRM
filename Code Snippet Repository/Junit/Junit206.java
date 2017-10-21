	@Test
	void assertArrayEqualsDifferentBooleanArrays() {
		try {
			assertArrayEquals(new boolean[] { true, false, false }, new boolean[] { true, false, true });
			expectAssertionFailedError();
		}
		catch (AssertionFailedError ex) {
			assertMessageEquals(ex, "array contents differ at index [2], expected: <false> but was: <true>");
		}
	}

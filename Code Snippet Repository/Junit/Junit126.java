	@Test
	void assertArrayEqualsDifferentObjectArrays() {
		try {
			assertArrayEquals(new Object[] { 1L, "2", '3', 4, 5D }, new Object[] { 1L, "2", '9', 4, 5D });
			expectAssertionFailedError();
		}
		catch (AssertionFailedError ex) {
			assertMessageEquals(ex, "array contents differ at index [2], expected: <3> but was: <9>");
		}

		try {
			assertArrayEquals(new Object[] { "a", 10, 11, 12, Double.NaN }, new Object[] { "a", 10, 11, 12, 13.55D });
			expectAssertionFailedError();
		}
		catch (AssertionFailedError ex) {
			assertMessageEquals(ex, "array contents differ at index [4], expected: <NaN> but was: <13.55>");
		}
	}

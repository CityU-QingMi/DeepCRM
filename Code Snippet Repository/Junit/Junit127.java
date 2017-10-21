	@Test
	void assertArrayEqualsDifferentNestedObjectArrays() {
		try {
			assertArrayEquals(
				new Object[] { 1, 2, new Object[] { 3, new Object[] { 4, new boolean[] { false, true } } } },
				new Object[] { 1, 2, new Object[] { 3, new Object[] { 4, new boolean[] { true, false } } } });
			expectAssertionFailedError();
		}
		catch (AssertionFailedError ex) {
			assertMessageEquals(ex, "array contents differ at index [2][1][1][0], expected: <false> but was: <true>");
		}

		try {
			assertArrayEquals(new Object[] { 1, 2, 3, new Object[] { new Object[] { 4, new Object[] { 5 } } } },
				new Object[] { 1, 2, 3, new Object[] { new Object[] { 4, new Object[] { new Object[] {} } } } });
			expectAssertionFailedError();
		}
		catch (AssertionFailedError ex) {
			assertMessageEquals(ex, "array contents differ at index [3][0][1][0], expected: <5> but was: <[]>");
		}
	}

	@Test
	void assertArrayEqualsBooleanArraysOfDifferentLength() {
		try {
			assertArrayEquals(new boolean[] { true, false }, new boolean[] { true, false, true });
			expectAssertionFailedError();
		}
		catch (AssertionFailedError ex) {
			assertMessageEquals(ex, "array lengths differ, expected: <2> but was: <3>");
		}
	}

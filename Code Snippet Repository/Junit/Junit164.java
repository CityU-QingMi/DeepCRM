	@Test
	void assertArrayEqualsIntArraysOfDifferentLength() {
		try {
			assertArrayEquals(new int[] { 1, 2, 3, Integer.MIN_VALUE, 4 }, new int[] { 1, Integer.MAX_VALUE, 2 });
			expectAssertionFailedError();
		}
		catch (AssertionFailedError ex) {
			assertMessageEquals(ex, "array lengths differ, expected: <5> but was: <3>");
		}
	}

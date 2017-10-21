	@Test
	void assertArrayEqualsShortArraysOfDifferentLength() {
		try {
			assertArrayEquals(new short[] { 1, 2, 3, 4, 5, 6 }, new short[] { 1, 2, 3 });
			expectAssertionFailedError();
		}
		catch (AssertionFailedError ex) {
			assertMessageEquals(ex, "array lengths differ, expected: <6> but was: <3>");
		}
	}

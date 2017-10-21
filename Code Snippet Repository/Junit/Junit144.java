	@Test
	void assertArrayEqualsByteArraysOfDifferentLength() {
		try {
			assertArrayEquals(new byte[] { 1, 2, 100 }, new byte[] { 1, 2, 100, 101 });
			expectAssertionFailedError();
		}
		catch (AssertionFailedError ex) {
			assertMessageEquals(ex, "array lengths differ, expected: <3> but was: <4>");
		}
	}

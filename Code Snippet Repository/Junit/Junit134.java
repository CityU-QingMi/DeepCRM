	@Test
	void assertArrayEqualsCharArraysOfDifferentLength() {
		try {
			assertArrayEquals(new char[] { 'q', 'w', 'e' }, new char[] { 'q', 'w' });
			expectAssertionFailedError();
		}
		catch (AssertionFailedError ex) {
			assertMessageEquals(ex, "array lengths differ, expected: <3> but was: <2>");
		}
	}

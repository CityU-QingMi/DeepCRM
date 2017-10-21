	@Test
	void assertArrayEqualsObjectArraysOfDifferentLength() {
		try {
			assertArrayEquals(new Object[] { 'a', "b", 'c' }, new Object[] { 'a', "b", 'c', 1 });
			expectAssertionFailedError();
		}
		catch (AssertionFailedError ex) {
			assertMessageEquals(ex, "array lengths differ, expected: <3> but was: <4>");
		}
	}

	@Test
	void assertArrayEqualsDifferentCharArrays() {
		try {
			assertArrayEquals(new char[] { 'a', 'b', 'c' }, new char[] { 'a', 'b', 'a' });
			expectAssertionFailedError();
		}
		catch (AssertionFailedError ex) {
			assertMessageEquals(ex, "array contents differ at index [2], expected: <c> but was: <a>");
		}
	}

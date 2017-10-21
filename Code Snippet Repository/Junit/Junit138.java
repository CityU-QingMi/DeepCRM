	@Test
	void assertArrayEqualsDifferentCharArraysAndMessage() {
		try {
			assertArrayEquals(new char[] { 'z', 'x', 'c', 'v' }, new char[] { 'x', 'x', 'c', 'v' }, "message");
			expectAssertionFailedError();
		}
		catch (AssertionFailedError ex) {
			assertMessageStartsWith(ex, "message");
			assertMessageEndsWith(ex, "array contents differ at index [0], expected: <z> but was: <x>");
		}
	}

	@Test
	void assertArrayEqualsDifferentCharArraysAndMessageSupplier() {
		try {
			assertArrayEquals(new char[] { 'r', 't', 'y' }, new char[] { 'r', 'y', 'u' }, () -> "message");
			expectAssertionFailedError();
		}
		catch (AssertionFailedError ex) {
			assertMessageStartsWith(ex, "message");
			assertMessageEndsWith(ex, "array contents differ at index [1], expected: <t> but was: <y>");
		}
	}

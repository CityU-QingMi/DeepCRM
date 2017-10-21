	@Test
	void assertArrayEqualsNestedObjectArraysOfDifferentLengthAndMessage() {
		try {
			assertArrayEquals(//
				new Object[] { 'a', 1, new Object[] { 2, 3 } }, //
				new Object[] { 'a', 1, new Object[] { 2, 3, 4, 5 } }, //
				"message");
			expectAssertionFailedError();
		}
		catch (AssertionFailedError ex) {
			assertMessageStartsWith(ex, "message");
			assertMessageEndsWith(ex, "array lengths differ at index [2], expected: <2> but was: <4>");
		}
	}

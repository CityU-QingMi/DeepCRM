	@Test
	void assertArrayEqualsNestedObjectArraysOfDifferentLength() {
		try {
			assertArrayEquals(
				new Object[] { "a", new Object[] { "b", new Object[] { "c", "d", new Object[] { "e", 1, 2, 3 } } } },
				new Object[] { "a",
						new Object[] { "b", new Object[] { "c", "d", new Object[] { "e", 1, 2, 3, 4, 5 } } } });
			expectAssertionFailedError();
		}
		catch (AssertionFailedError ex) {
			assertMessageEquals(ex, "array lengths differ at index [1][1][2], expected: <4> but was: <6>");
		}

		try {
			assertArrayEquals(
				new Object[] { new Object[] {
						new Object[] { new Object[] { new Object[] { new Object[] { new char[] { 'a' } } } } } } },
				new Object[] { new Object[] { new Object[] {
						new Object[] { new Object[] { new Object[] { new char[] { 'a', 'b' } } } } } } });
			expectAssertionFailedError();
		}
		catch (AssertionFailedError ex) {
			assertMessageEquals(ex, "array lengths differ at index [0][0][0][0][0][0], expected: <1> but was: <2>");
		}
	}

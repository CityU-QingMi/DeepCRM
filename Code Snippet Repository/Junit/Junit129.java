	@Test
	void assertArrayEqualsDifferentNestedObjectArraysAndMessage() {
		try {
			assertArrayEquals(new Object[] { 9, 8, '6', new Object[] { 5, 4, "3", new Object[] { "2", '1' } } },
				new Object[] { 9, 8, '6', new Object[] { 5, 4, "3", new Object[] { "99", '1' } } }, "message");
			expectAssertionFailedError();
		}
		catch (AssertionFailedError ex) {
			assertMessageStartsWith(ex, "message");
			assertMessageEndsWith(ex, "array contents differ at index [3][3][0], expected: <2> but was: <99>");
		}

		try {
			assertArrayEquals(new Object[] { 9, 8, '6', new Object[] { 5, 4, "3", new String[] { "2", "1" } } },
				new Object[] { 9, 8, '6', new Object[] { 5, 4, "3", new String[] { "99", "1" } } }, "message");
			expectAssertionFailedError();
		}
		catch (AssertionFailedError ex) {
			assertMessageStartsWith(ex, "message");
			assertMessageEndsWith(ex, "array contents differ at index [3][3][0], expected: <2> but was: <99>");
		}
	}

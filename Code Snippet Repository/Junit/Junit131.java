	@Test
	void assertArrayEqualsDifferentNestedObjectArraysAndMessageSupplier() {
		try {
			assertArrayEquals(
				new Object[] { "one", 1L, new Object[] { "a", 'b', new Object[] { 1, new Object[] { 2, 3 } } }, "abc" },
				new Object[] { "one", 1L, new Object[] { "a", 'b', new Object[] { 1, new Object[] { 2, 4 } } }, "abc" },
				() -> "message");
			expectAssertionFailedError();
		}
		catch (AssertionFailedError ex) {
			assertMessageStartsWith(ex, "message");
			assertMessageEndsWith(ex, "array contents differ at index [2][2][1][1], expected: <3> but was: <4>");
		}

		try {
			assertArrayEquals(
				new Object[] { "j", new String[] { "a" }, new int[] { 42 }, "ab", new Object[] { 1, new int[] { 3 } } },
				new Object[] { "j", new String[] { "a" }, new int[] { 42 }, "ab", new Object[] { 1, new int[] { 5 } } },
				() -> "message");
			expectAssertionFailedError();
		}
		catch (AssertionFailedError ex) {
			assertMessageStartsWith(ex, "message");
			assertMessageEndsWith(ex, "array contents differ at index [4][1][0], expected: <3> but was: <5>");
		}
	}

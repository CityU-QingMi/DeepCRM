	@Test
	void assertIterableEqualsDifferentNestedIterablesAndMessageSupplier() {
		try {
			assertIterableEquals(setOf("one", 1L, setOf("a", 'b', setOf(1, setOf(2, 3))), "abc"),
				setOf("one", 1L, setOf("a", 'b', setOf(1, setOf(2, 4))), "abc"), () -> "message");
			expectAssertionFailedError();
		}
		catch (AssertionFailedError ex) {
			assertMessageStartsWith(ex, "message");
			assertMessageEndsWith(ex, "iterable contents differ at index [2][2][1][1], expected: <3> but was: <4>");
		}

		try {
			assertIterableEquals(listOf("j", listOf("a"), setOf(42), "ab", setOf(1, listOf(3))),
				listOf("j", listOf("a"), setOf(42), "ab", setOf(1, listOf(5))), () -> "message");
			expectAssertionFailedError();
		}
		catch (AssertionFailedError ex) {
			assertMessageStartsWith(ex, "message");
			assertMessageEndsWith(ex, "iterable contents differ at index [4][1][0], expected: <3> but was: <5>");
		}
	}

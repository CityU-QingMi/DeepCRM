	@Test
	void assertIterableEqualsNestedIterablesOfDifferentLength() {
		try {
			assertIterableEquals(listOf("a", setOf("b", listOf("c", "d", setOf("e", 1, 2, 3)))),
				listOf("a", setOf("b", listOf("c", "d", setOf("e", 1, 2, 3, 4, 5)))));
			expectAssertionFailedError();
		}
		catch (AssertionFailedError ex) {
			assertMessageEquals(ex, "iterable lengths differ at index [1][1][2], expected: <4> but was: <6>");
		}

		try {
			assertIterableEquals(listOf(listOf(listOf(listOf(listOf(listOf(listOf('a'))))))),
				listOf(listOf(listOf(listOf(listOf(listOf(listOf('a', 'b'))))))));
			expectAssertionFailedError();
		}
		catch (AssertionFailedError ex) {
			assertMessageEquals(ex, "iterable lengths differ at index [0][0][0][0][0][0], expected: <1> but was: <2>");
		}
	}

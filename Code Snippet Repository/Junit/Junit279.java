	@Test
	void assertIterableEqualsNestedIterableVsNull() {
		try {
			assertIterableEquals(listOf(listOf(), 1, "2", setOf('3', listOf((List<Object>) null))),
				listOf(listOf(), 1, "2", setOf('3', listOf(listOf("4")))));
			expectAssertionFailedError();
		}
		catch (AssertionFailedError ex) {
			assertMessageEquals(ex, "expected iterable was <null> at index [3][1][0]");
		}

		try {
			assertIterableEquals(setOf(1, 2, listOf(3, listOf("4", setOf(5, setOf(6)))), "7"),
				setOf(1, 2, listOf(3, listOf("4", setOf(5, null))), "7"));
			expectAssertionFailedError();
		}
		catch (AssertionFailedError ex) {
			assertMessageEquals(ex, "actual iterable was <null> at index [2][1][1][1]");
		}
	}

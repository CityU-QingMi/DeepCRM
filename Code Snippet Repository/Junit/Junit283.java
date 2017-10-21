	@Test
	void assertIterableEqualsNestedIterableVsNullAndMessageSupplier() {
		try {
			assertIterableEquals(listOf("1", "2", "3", listOf("4", listOf((List<Object>) null))),
				listOf("1", "2", "3", listOf("4", listOf(listOf(5)))), () -> "message");
			expectAssertionFailedError();
		}
		catch (AssertionFailedError ex) {
			assertMessageStartsWith(ex, "message");
			assertMessageEndsWith(ex, "expected iterable was <null> at index [3][1][0]");
		}

		try {
			assertIterableEquals(setOf(1, 2, setOf("3", setOf('4', setOf(5, 6, setOf())))),
				setOf(1, 2, setOf("3", setOf('4', setOf(5, 6, null)))), () -> "message");
			expectAssertionFailedError();
		}
		catch (AssertionFailedError ex) {
			assertMessageStartsWith(ex, "message");
			assertMessageEndsWith(ex, "actual iterable was <null> at index [2][1][1][2]");
		}
	}

	@Test
	void assertIterableEqualsNestedIterableVsNullAndMessage() {
		try {
			assertIterableEquals(listOf(1, listOf(2, 3, listOf(4, 5, listOf((List<Object>) null)))),
				listOf(1, listOf(2, 3, listOf(4, 5, listOf(listOf(6))))), "message");
			expectAssertionFailedError();
		}
		catch (AssertionFailedError ex) {
			assertMessageStartsWith(ex, "message");
			assertMessageEndsWith(ex, "expected iterable was <null> at index [1][2][2][0]");
		}

		try {
			assertIterableEquals(listOf(1, listOf(2, listOf(3, listOf(listOf(4))))),
				listOf(1, listOf(2, listOf(3, listOf((List<Object>) null)))), "message");
			expectAssertionFailedError();
		}
		catch (AssertionFailedError ex) {
			assertMessageStartsWith(ex, "message");
			assertMessageEndsWith(ex, "actual iterable was <null> at index [1][1][1][0]");
		}
	}

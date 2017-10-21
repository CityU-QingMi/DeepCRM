	@Test
	void assertIterableEqualsDifferentNestedIterables() {
		try {
			assertIterableEquals(listOf(1, 2, listOf(3, listOf(4, listOf(false, true)))),
				listOf(1, 2, listOf(3, listOf(4, listOf(true, false)))));
			expectAssertionFailedError();
		}
		catch (AssertionFailedError ex) {
			assertMessageEquals(ex,
				"iterable contents differ at index [2][1][1][0], expected: <false> but was: <true>");
		}

		List<Object> differentElement = listOf();
		try {
			assertIterableEquals(listOf(1, 2, 3, listOf(listOf(4, listOf(5)))),
				listOf(1, 2, 3, listOf(listOf(4, listOf(differentElement)))));
			expectAssertionFailedError();
		}
		catch (AssertionFailedError ex) {
			assertMessageEquals(ex,
				"iterable contents differ at index [3][0][1][0], expected: <5> but was: <" + differentElement + ">");
		}
	}

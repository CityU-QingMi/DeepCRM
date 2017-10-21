	@Test
	void assertIterableEqualsDifferentNestedIterablesAndMessage() {
		try {
			assertIterableEquals(listOf(9, 8, '6', listOf(5, 4, "3", listOf("2", '1'))),
				listOf(9, 8, '6', listOf(5, 4, "3", listOf("99", '1'))), "message");
			expectAssertionFailedError();
		}
		catch (AssertionFailedError ex) {
			assertMessageStartsWith(ex, "message");
			assertMessageEndsWith(ex, "iterable contents differ at index [3][3][0], expected: <2> but was: <99>");
		}

		try {
			assertIterableEquals(listOf(9, 8, '6', listOf(5, 4, "3", listOf("2", "1"))),
				listOf(9, 8, '6', listOf(5, 4, "3", listOf("99", "1"))), "message");
			expectAssertionFailedError();
		}
		catch (AssertionFailedError ex) {
			assertMessageStartsWith(ex, "message");
			assertMessageEndsWith(ex, "iterable contents differ at index [3][3][0], expected: <2> but was: <99>");
		}
	}

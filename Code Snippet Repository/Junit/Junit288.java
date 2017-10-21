	@Test
	void assertLinesMatchMoreActualLinesThenExpectedFails() {
		List<String> expected = Arrays.asList("first line", "second line", "third line");
		List<String> actual = Arrays.asList("first line", "second line", "third line", "last line");
		Error error = assertThrows(AssertionFailedError.class, () -> assertLinesMatch(expected, actual));
		List<String> expectedErrorMessageLines = Arrays.asList( //
			"more actual lines than expected: 1 ==> expected: <first line", //
			"second line", //
			"third line> but was: <first line", //
			"second line", //
			"third line", "last line>");
		assertLinesMatch(expectedErrorMessageLines, Arrays.asList(error.getMessage().split("\\R")));
	}

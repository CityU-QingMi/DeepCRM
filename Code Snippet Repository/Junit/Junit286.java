	@Test
	void assertLinesMatchMoreExpectedThanActualAvailableFails() {
		List<String> expected = Arrays.asList("first line", "second line", "third line");
		List<String> actual = Arrays.asList("first line", "third line");
		Error error = assertThrows(AssertionFailedError.class, () -> assertLinesMatch(expected, actual));
		List<String> expectedErrorMessageLines = Arrays.asList( //
			"expected 3 lines, but only got 2 ==> expected: <first line", //
			"second line", //
			"third line> but was: <first line", //
			"third line>");
		assertLinesMatch(expectedErrorMessageLines, Arrays.asList(error.getMessage().split("\\R")));
	}

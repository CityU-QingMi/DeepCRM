	@Test
	void assertLinesMatchFailsWithDescriptiveErrorMessage() {
		List<String> expected = Arrays.asList("first line", "second line", "third line");
		List<String> actual = Arrays.asList("first line", "sec0nd line", "third line");
		Error error = assertThrows(AssertionFailedError.class, () -> assertLinesMatch(expected, actual));
		List<String> expectedErrorMessageLines = Arrays.asList( //
			"expected line #2:`second line` doesn't match ==> expected: <first line", //
			"second line", //
			"third line> but was: <first line", //
			"sec0nd line", //
			"third line>");
		assertLinesMatch(expectedErrorMessageLines, Arrays.asList(error.getMessage().split("\\R")));
	}

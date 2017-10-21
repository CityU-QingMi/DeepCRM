	@Test
	void assertLinesMatchUsingFastForwardMarkerWithoutMatchingNextLineFails() {
		List<String> expected = Arrays.asList("first line", ">> fails, because next line is >>", "not present");
		List<String> actual = Arrays.asList("first line", "skipped", "last line");
		Error error = assertThrows(AssertionFailedError.class, () -> assertLinesMatch(expected, actual));
		List<String> expectedErrorMessageLines = Arrays.asList( //
			"fast-forward(∞) didn't find: `not present` ==> expected: <first line", //
			">> fails, because next line is >>", //
			"not present> but was: <first line", //
			"skipped", //
			"last line>");
		assertLinesMatch(expectedErrorMessageLines, Arrays.asList(error.getMessage().split("\\R")));
	}

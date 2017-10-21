	@Test
	void assertLinesMatchUsingFastForwardMarkerWithTooHighLimitFails() {
		List<String> expected = Arrays.asList("first line", ">> 100 >>");
		List<String> actual = Arrays.asList("first line", "skipped", "last line");
		Error error = assertThrows(AssertionFailedError.class, () -> assertLinesMatch(expected, actual));
		List<String> expectedErrorMessageLines = Arrays.asList( //
			"terminal fast-forward(100) error: fast-forward(2) expected ==> expected: <first line", //
			">> 100 >>> but was: <first line", //
			"skipped", //
			"last line>");
		assertLinesMatch(expectedErrorMessageLines, Arrays.asList(error.getMessage().split("\\R")));
	}

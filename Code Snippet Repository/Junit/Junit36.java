	private static void fail(List<String> expectedLines, List<String> actualLines, String format, Object... args) {
		if (expectedLines.size() > MAX_SNIPPET_LENGTH) {
			expectedLines.subList(0, MAX_SNIPPET_LENGTH);
		}
		if (actualLines.size() > MAX_SNIPPET_LENGTH) {
			actualLines.subList(0, MAX_SNIPPET_LENGTH);
		}
		// use standard assertEquals(Object, Object, message) to let IDEs present the textual difference
		String expected = join(System.lineSeparator(), expectedLines);
		String actual = join(System.lineSeparator(), actualLines);
		assertEquals(expected, actual, format(format, args));
	}

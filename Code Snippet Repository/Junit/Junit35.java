	static void assertLinesMatch(List<String> expectedLines, List<String> actualLines) {
		notNull(expectedLines, "expectedLines must not be null");
		notNull(actualLines, "actualLines must not be null");

		// trivial case: same list instance
		if (expectedLines == actualLines) {
			return;
		}

		int expectedSize = expectedLines.size();
		int actualSize = actualLines.size();

		// trivial case: when expecting more than actual lines available, something is wrong
		if (expectedSize > actualSize) {
			fail(expectedLines, actualLines, "expected %d lines, but only got %d", expectedSize, actualSize);
		}

		// simple case: both list are equally sized, compare them line-by-line
		if (expectedSize == actualSize) {
			if (IntStream.range(0, expectedSize).allMatch(i -> matches(expectedLines.get(i), actualLines.get(i)))) {
				return;
			}
		}

		assertLinesMatchWithFastForward(expectedLines, actualLines);
	}

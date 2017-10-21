	private static void assertArraysHaveSameLength(int expected, int actual, Deque<Integer> indexes,
			Supplier<String> messageSupplier) {

		if (expected != actual) {
			String prefix = buildPrefix(nullSafeGet(messageSupplier));
			String message = "array lengths differ" + formatIndexes(indexes) + ", expected: <" + expected
					+ "> but was: <" + actual + ">";
			fail(prefix + message);
		}
	}

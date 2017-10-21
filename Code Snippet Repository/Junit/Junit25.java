	private static void assertArrayEquals(short[] expected, short[] actual, Deque<Integer> indexes,
			Supplier<String> messageSupplier) {

		if (expected == actual) {
			return;
		}
		assertArraysNotNull(expected, actual, indexes, messageSupplier);
		assertArraysHaveSameLength(expected.length, actual.length, indexes, messageSupplier);

		for (int i = 0; i < expected.length; i++) {
			if (expected[i] != actual[i]) {
				failArraysNotEqual(expected[i], actual[i], nullSafeIndexes(indexes, i), messageSupplier);
			}
		}
	}

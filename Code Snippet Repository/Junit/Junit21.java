	private static void assertArraysNotNull(Object expected, Object actual, Deque<Integer> indexes,
			Supplier<String> messageSupplier) {

		if (expected == null) {
			failExpectedArrayIsNull(indexes, messageSupplier);
		}
		if (actual == null) {
			failActualArrayIsNull(indexes, messageSupplier);
		}
	}

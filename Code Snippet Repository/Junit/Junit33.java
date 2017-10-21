	private static void assertIterablesNotNull(Object expected, Object actual, Deque<Integer> indexes,
			Supplier<String> messageSupplier) {

		if (expected == null) {
			failExpectedIterableIsNull(indexes, messageSupplier);
		}
		if (actual == null) {
			failActualIterableIsNull(indexes, messageSupplier);
		}
	}

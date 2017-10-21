	private static void assertIterableElementsEqual(Object expected, Object actual, Deque<Integer> indexes,
			Supplier<String> messageSupplier) {
		if (expected instanceof Iterable && actual instanceof Iterable) {
			assertIterableEquals((Iterable<?>) expected, (Iterable<?>) actual, indexes, messageSupplier);
		}
		else if (!Objects.equals(expected, actual)) {
			assertIterablesNotNull(expected, actual, indexes, messageSupplier);
			failIterablesNotEqual(expected, actual, indexes, messageSupplier);
		}
	}

	private static void assertIterableEquals(Iterable<?> expected, Iterable<?> actual, Deque<Integer> indexes,
			Supplier<String> messageSupplier) {

		if (expected == actual) {
			return;
		}
		assertIterablesNotNull(expected, actual, indexes, messageSupplier);

		Iterator<?> expectedIterator = expected.iterator();
		Iterator<?> actualIterator = actual.iterator();

		int processed = 0;
		while (expectedIterator.hasNext() && actualIterator.hasNext()) {
			processed++;
			Object expectedElement = expectedIterator.next();
			Object actualElement = actualIterator.next();

			if (expectedElement == actualElement) {
				continue;
			}

			indexes.addLast(processed - 1);
			assertIterableElementsEqual(expectedElement, actualElement, indexes, messageSupplier);
			indexes.removeLast();
		}

		assertIteratorsAreEmpty(expectedIterator, actualIterator, processed, indexes, messageSupplier);
	}

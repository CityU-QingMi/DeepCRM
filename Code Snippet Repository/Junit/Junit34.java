	private static void assertIteratorsAreEmpty(Iterator<?> expected, Iterator<?> actual, int processed,
			Deque<Integer> indexes, Supplier<String> messageSupplier) {

		if (expected.hasNext() || actual.hasNext()) {
			AtomicInteger expectedCount = new AtomicInteger(processed);
			expected.forEachRemaining(e -> expectedCount.incrementAndGet());

			AtomicInteger actualCount = new AtomicInteger(processed);
			actual.forEachRemaining(e -> actualCount.incrementAndGet());

			String prefix = buildPrefix(nullSafeGet(messageSupplier));
			String message = "iterable lengths differ" + formatIndexes(indexes) + ", expected: <" + expectedCount.get()
					+ "> but was: <" + actualCount.get() + ">";
			fail(prefix + message);
		}
	}

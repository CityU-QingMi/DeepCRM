	@Test
	void concurrentAccessToDefaultStoreWithoutParentStore() {
		// Run the actual test 100 times "for good measure".
		IntStream.range(1, 100).forEach(i -> {
			Store store = reset();
			// Simulate 100 extensions interacting concurrently with the Store.
			IntStream.range(1, 100).parallel().forEach(j -> store.getOrComputeIfAbsent("key", this::newValue));
			assertEquals(1, count.get(), () -> "number of times newValue() was invoked in run #" + i);
		});
	}

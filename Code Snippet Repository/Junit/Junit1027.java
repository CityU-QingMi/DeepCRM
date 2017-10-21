	@Test
	void pruneGroup() {
		final AtomicInteger countVisited = new AtomicInteger();
		TestDescriptor.Visitor visitor = descriptor -> {
			if (descriptor.getUniqueId().equals(UniqueId.root("group", "group1")))
				descriptor.removeFromHierarchy();
			countVisited.incrementAndGet();
		};
		engineDescriptor.accept(visitor);

		assertEquals(4, countVisited.get(), "Children of pruned element are not visited");

		List<UniqueId> visited = new ArrayList<>();
		engineDescriptor.accept(descriptor -> visited.add(descriptor.getUniqueId()));

		assertEquals(3, visited.size());
		assertFalse(visited.contains(UniqueId.root("group", "group1")));
	}

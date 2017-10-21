	@Test
	void pruneLeaf() {
		TestDescriptor.Visitor visitor = descriptor -> {
			if (descriptor.getUniqueId().equals(UniqueId.root("leaf", "leaf1-1")))
				descriptor.removeFromHierarchy();
		};
		engineDescriptor.accept(visitor);

		List<UniqueId> visited = new ArrayList<>();
		engineDescriptor.accept(descriptor -> visited.add(descriptor.getUniqueId()));

		assertEquals(7, visited.size());
		assertTrue(visited.contains(UniqueId.root("group", "group1")));
		assertFalse(visited.contains(UniqueId.root("leaf", "leaf1-1")));
	}

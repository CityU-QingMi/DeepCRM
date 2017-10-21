	@Test
	void removeFromHierarchyClearsParentFromAllChildren() {
		TestDescriptor group = engineDescriptor.getChildren().iterator().next();
		assertSame(engineDescriptor, group.getParent().orElseThrow(Error::new));
		assertTrue(group.getChildren().stream().allMatch(d -> d.getParent().orElseThrow(Error::new) == group));

		Set<? extends TestDescriptor> formerChildren = group.getChildren();
		group.removeFromHierarchy();

		assertFalse(group.getParent().isPresent());
		assertTrue(group.getChildren().isEmpty());
		assertTrue(formerChildren.stream().noneMatch(d -> d.getParent().isPresent()));
	}

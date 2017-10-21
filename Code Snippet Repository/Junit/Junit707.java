	public void add(TestIdentifier testIdentifier) {
		Preconditions.notNull(testIdentifier, "testIdentifier must not be null");
		allIdentifiers.put(testIdentifier.getUniqueId(), testIdentifier);
		if (testIdentifier.getParentId().isPresent()) {
			String parentId = testIdentifier.getParentId().get();
			Set<TestIdentifier> directChildren = children.computeIfAbsent(parentId,
				key -> Collections.synchronizedSet(new LinkedHashSet<>(16)));
			directChildren.add(testIdentifier);
		}
		else {
			roots.add(testIdentifier);
		}
	}

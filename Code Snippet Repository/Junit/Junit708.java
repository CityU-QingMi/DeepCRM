	public Set<TestIdentifier> getDescendants(TestIdentifier parent) {
		Preconditions.notNull(parent, "parent must not be null");
		Set<TestIdentifier> result = new LinkedHashSet<>(16);
		Set<TestIdentifier> children = getChildren(parent);
		result.addAll(children);
		for (TestIdentifier child : children) {
			result.addAll(getDescendants(child));
		}
		return unmodifiableSet(result);
	}

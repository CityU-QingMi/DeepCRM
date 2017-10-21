	private Optional<TestIdentifier> findAncestor(Optional<TestIdentifier> testIdentifier,
			Predicate<TestIdentifier> predicate) {
		Optional<TestIdentifier> current = testIdentifier;
		while (current.isPresent()) {
			if (predicate.test(current.get())) {
				return current;
			}
			current = this.testPlan.getParent(current.get());
		}
		return Optional.empty();
	}

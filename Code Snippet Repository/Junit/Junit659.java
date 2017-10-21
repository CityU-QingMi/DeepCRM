	Optional<TestExecutionResult> getResult(TestIdentifier testIdentifier) {
		if (this.finishedTests.containsKey(testIdentifier)) {
			return Optional.of(this.finishedTests.get(testIdentifier));
		}
		Optional<TestIdentifier> parent = this.testPlan.getParent(testIdentifier);
		Optional<TestIdentifier> ancestor = findAncestor(parent, this.finishedTests::containsKey);
		if (ancestor.isPresent()) {
			TestExecutionResult result = this.finishedTests.get(ancestor.get());
			if (result.getStatus() != SUCCESSFUL) {
				return Optional.of(result);
			}
		}
		return Optional.empty();
	}

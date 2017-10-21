	private static TestIdentifier identifiersAsParentOnTestPlan(TestPlan plan, TestDescriptor parent,
			TestDescriptor child) {
		child.setParent(parent);

		TestIdentifier parentIdentifier = TestIdentifier.from(parent);
		TestIdentifier childIdentifier = TestIdentifier.from(child);

		plan.add(parentIdentifier);
		plan.add(childIdentifier);

		return childIdentifier;
	}

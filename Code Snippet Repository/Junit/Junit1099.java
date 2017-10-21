	private TestIdentifier createContainerIdentifier(String uniqueId) {
		TestIdentifier identifier = TestIdentifier.from(
			new TestDescriptorStub(UniqueId.root("container", uniqueId), uniqueId) {

				@Override
				public Type getType() {
					return Type.CONTAINER;
				}
			});
		testPlan.add(identifier);
		return identifier;
	}

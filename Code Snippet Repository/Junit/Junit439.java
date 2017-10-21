	private AbstractTestDescriptor containerTestDescriptorWithTags(UniqueId uniqueId, Set<TestTag> tags) {
		return new AbstractTestDescriptor(uniqueId, "testDescriptor with tags") {

			@Override
			public Type getType() {
				return Type.CONTAINER;
			}

			@Override
			public Set<TestTag> getTags() {
				return tags;
			}
		};
	}

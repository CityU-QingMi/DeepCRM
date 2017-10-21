	static JupiterTestDescriptor createDynamicDescriptor(JupiterTestDescriptor parent, DynamicNode node, int index,
			TestSource source) {
		JupiterTestDescriptor descriptor;
		if (node instanceof DynamicTest) {
			DynamicTest test = (DynamicTest) node;
			UniqueId uniqueId = parent.getUniqueId().append(DYNAMIC_TEST_SEGMENT_TYPE, "#" + index);
			descriptor = new DynamicTestTestDescriptor(uniqueId, test, source);
		}
		else {
			DynamicContainer container = (DynamicContainer) node;
			UniqueId uniqueId = parent.getUniqueId().append(DYNAMIC_CONTAINER_SEGMENT_TYPE, "#" + index);
			descriptor = new DynamicContainerTestDescriptor(uniqueId, container, source);
		}
		parent.addChild(descriptor);
		return descriptor;
	}

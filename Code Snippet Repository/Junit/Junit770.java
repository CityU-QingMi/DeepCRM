	private VintageTestDescriptor registerDynamicTestDescriptor(Description description) {
		// workaround for dynamic children as used by Spock's Runner
		TestDescriptor parent = findParent(description);
		UniqueId uniqueId = parent.getUniqueId().append(SEGMENT_TYPE_DYNAMIC, uniqueIdExtractor.apply(description));
		VintageTestDescriptor dynamicDescriptor = new VintageTestDescriptor(uniqueId, description);
		parent.addChild(dynamicDescriptor);
		testRun.registerDynamicTest(dynamicDescriptor);
		dynamicTestRegistered(dynamicDescriptor);
		return dynamicDescriptor;
	}

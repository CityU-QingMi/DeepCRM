	@Override
	public TestDescriptor discover(EngineDiscoveryRequest discoveryRequest, UniqueId uniqueId) {
		this.discoveryRequestForDiscovery = discoveryRequest;
		this.uniqueIdForDiscovery = uniqueId;

		UniqueId engineUniqueId = UniqueId.forEngine(ID);
		TestDescriptorStub engineDescriptor = new TestDescriptorStub(engineUniqueId, ID);
		TestDescriptorStub testDescriptor = new TestDescriptorStub(engineUniqueId.append("test", "test"), "test");
		engineDescriptor.addChild(testDescriptor);
		return engineDescriptor;
	}

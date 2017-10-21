	@Test
	void prunesTestDescriptorsAfterApplyingPostDiscoveryFilters() {
		TestEngineSpy engine = new TestEngineSpy() {

			@Override
			public TestDescriptor discover(EngineDiscoveryRequest discoveryRequest, UniqueId uniqueId) {
				super.discover(discoveryRequest, uniqueId);
				TestDescriptorStub engineDescriptor = new TestDescriptorStub(uniqueId, uniqueId.toString());
				TestDescriptorStub containerDescriptor = new TestDescriptorStub(uniqueId.append("container", "a"),
					"container") {

					@Override
					public Type getType() {
						return Type.CONTAINER;
					}
				};
				containerDescriptor.addChild(
					new TestDescriptorStub(containerDescriptor.getUniqueId().append("test", "b"), "test"));
				engineDescriptor.addChild(containerDescriptor);
				return engineDescriptor;
			}
		};

		DefaultLauncher launcher = createLauncher(engine);
		TestPlan testPlan = launcher.discover(request().filters(
			(PostDiscoveryFilter) testDescriptor -> FilterResult.includedIf(testDescriptor.isContainer())).build());

		assertThat(testPlan.getRoots()).hasSize(1);
		TestIdentifier engineIdentifier = getOnlyElement(testPlan.getRoots());
		assertThat(testPlan.getChildren(engineIdentifier)).isEmpty();
	}

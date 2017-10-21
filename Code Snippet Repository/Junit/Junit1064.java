	@Test
	void discoverTestPlanForEngineThatReturnsNullForItsRootDescriptor() {
		TestEngine engine = new TestEngineStub() {

			@Override
			public TestDescriptor discover(org.junit.platform.engine.EngineDiscoveryRequest discoveryRequest,
					UniqueId uniqueId) {
				return null;
			}
		};

		TestPlan testPlan = createLauncher(engine).discover(request().build());
		assertThat(testPlan.getRoots()).hasSize(0);
	}

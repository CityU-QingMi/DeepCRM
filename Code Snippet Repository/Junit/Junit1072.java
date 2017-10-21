	@Test
	void discoverTestPlanForEngineThatThrowsAnErrorInDiscoverPhase() {
		TestEngine engine = new TestEngineStub() {

			@Override
			public TestDescriptor discover(org.junit.platform.engine.EngineDiscoveryRequest discoveryRequest,
					UniqueId uniqueId) {
				throw new Error("ignored");
			}
		};

		TestPlan testPlan = createLauncher(engine).discover(request().build());
		assertThat(testPlan.getRoots()).hasSize(0);
	}

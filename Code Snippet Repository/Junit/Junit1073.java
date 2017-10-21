	@Test
	void discoverTestPlanForEngineThatThrowsRuntimeExceptionInDiscoverPhase() {
		TestEngine engine = new TestEngineStub() {

			@Override
			public TestDescriptor discover(org.junit.platform.engine.EngineDiscoveryRequest discoveryRequest,
					UniqueId uniqueId) {
				throw new RuntimeException("ignored");
			}
		};

		TestPlan testPlan = createLauncher(engine).discover(request().build());
		assertThat(testPlan.getRoots()).hasSize(0);
	}

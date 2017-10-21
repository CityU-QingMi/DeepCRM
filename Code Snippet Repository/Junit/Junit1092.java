		@Test
		void engineFiltersAreStoredInDiscoveryRequest() {
			TestEngine engine1 = new TestEngineStub("engine1");
			TestEngine engine2 = new TestEngineStub("engine2");
			TestEngine engine3 = new TestEngineStub("engine3");

			// @formatter:off
			LauncherDiscoveryRequest discoveryRequest = request()
					.filters(includeEngines(engine1.getId(), engine2.getId()))
					.build();
			// @formatter:on

			List<EngineFilter> filters = discoveryRequest.getEngineFilters();
			assertThat(filters).hasSize(1);
			EngineFilter engineFilter = filters.get(0);
			assertTrue(engineFilter.apply(engine1).included());
			assertTrue(engineFilter.apply(engine2).included());
			assertTrue(engineFilter.apply(engine3).excluded());
		}

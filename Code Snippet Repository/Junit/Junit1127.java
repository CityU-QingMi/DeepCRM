		@Test
		void addsEngineFiltersToRequestWhenIncludeEnginesOrExcludeEnginesAnnotationsArePresent() throws Exception {

			@IncludeEngines({ "foo", "bar", "baz" })
			@ExcludeEngines({ "bar", "quux" })
			class TestCase {
			}

			TestEngine fooEngine = new TestEngineStub("foo");
			TestEngine barEngine = new TestEngineStub("bar");
			TestEngine bazEngine = new TestEngineStub("baz");
			TestEngine quuxEngine = new TestEngineStub("quux");

			LauncherDiscoveryRequest request = instantiateRunnerAndCaptureGeneratedRequest(TestCase.class);

			List<EngineFilter> filters = request.getEngineFilters();
			assertThat(filters).hasSize(2);

			EngineFilter includeFilter = filters.get(0);
			assertTrue(includeFilter.apply(fooEngine).included());
			assertTrue(includeFilter.apply(barEngine).included());
			assertTrue(includeFilter.apply(bazEngine).included());
			assertTrue(includeFilter.apply(quuxEngine).excluded());

			EngineFilter excludeFilter = filters.get(1);
			assertTrue(excludeFilter.apply(fooEngine).included());
			assertTrue(excludeFilter.apply(barEngine).excluded());
			assertTrue(excludeFilter.apply(bazEngine).included());
			assertTrue(excludeFilter.apply(quuxEngine).excluded());
		}

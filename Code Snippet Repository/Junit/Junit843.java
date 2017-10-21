	@Test
	void packageNameFilterExcludesClasses() {
		// @formatter:off
		EngineDiscoveryRequest request = request()
				.selectors(selectClass(Foo.class), selectClass(Bar.class))
				.filters(PackageNameFilter.excludePackageNames("org.junit.vintage.engine.discovery"))
				.build();
		// @formatter:on

		VintageDiscoverer discoverer = new VintageDiscoverer();
		TestDescriptor testDescriptor = discoverer.discover(request, engineId());

		assertThat(testDescriptor.getChildren()).isEmpty();
	}

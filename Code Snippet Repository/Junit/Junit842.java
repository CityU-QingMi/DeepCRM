	@Test
	void classNameFilterExcludesClass() {
		// @formatter:off
		EngineDiscoveryRequest request = request()
				.selectors(selectClass(Foo.class), selectClass(Bar.class))
				.filters(ClassNameFilter.includeClassNamePatterns(".*Foo"))
				.build();
		// @formatter:on

		VintageDiscoverer discoverer = new VintageDiscoverer();
		TestDescriptor testDescriptor = discoverer.discover(request, engineId());

		assertThat(testDescriptor.getChildren()).hasSize(1);
		assertThat(getOnlyElement(testDescriptor.getChildren()).getUniqueId().toString()).contains(Foo.class.getName());
	}

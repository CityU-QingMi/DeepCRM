	@Test
	void nonMatchingClassesAreExcluded() {

		EngineDiscoveryRequest request = request().filters(
			ClassNameFilter.includeClassNamePatterns(".*\\$MatchingClass")).build();

		// @formatter:off
		TestDescriptor engineDescriptor = engineDescriptor()
				.with(
						classTestDescriptor("matching", MatchingClass.class),
						classTestDescriptor("other", OtherClass.class)
				)
				.build();

		applier.applyAllFilters(request, engineDescriptor);

		assertThat(engineDescriptor.getDescendants())
				.extracting(TestDescriptor::getUniqueId)
				.containsExactly(UniqueId.root("class", "matching"));
		// @formatter:on
	}

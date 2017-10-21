	@Test
	void nestedTestClassesAreAlwaysIncludedWhenTheirParentIs() {
		EngineDiscoveryRequest request = request().filters(
			ClassNameFilter.includeClassNamePatterns(".*\\$MatchingClass")).build();

		// @formatter:off
		TestDescriptor engineDescriptor = engineDescriptor()
				.with(
						classTestDescriptor("matching", MatchingClass.class)
								.with(nestedClassTestDescriptor("nested", MatchingClass.NestedClass.class))
				)
				.build();

		applier.applyAllFilters(request, engineDescriptor);

		assertThat(engineDescriptor.getDescendants())
				.extracting(TestDescriptor::getUniqueId)
				.containsExactlyInAnyOrder(
						UniqueId.root("class", "matching"),
						UniqueId.root("nested-class", "nested")
				);
		// @formatter:on
	}

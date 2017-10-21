	@Test
	void packageNameFilterInclude_matchingPackagesAreIncluded() {

		EngineDiscoveryRequest request = request().filters(
			PackageNameFilter.includePackageNames("org.junit.jupiter.engine")).build();

		// @formatter:off
		TestDescriptor engineDescriptor = engineDescriptor()
				.with(
						classTestDescriptor("matching", MatchingClass.class)
				)
				.build();

		applier.applyAllFilters(request, engineDescriptor);

		assertThat(engineDescriptor.getDescendants())
				.extracting(TestDescriptor::getUniqueId)
				.containsExactly(UniqueId.root("class", "matching"));
		// @formatter:on
	}

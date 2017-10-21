	@Test
	void packageNameFilterExclude_matchingPackagesAreExcluded() {

		EngineDiscoveryRequest request = request().filters(
			PackageNameFilter.excludePackageNames("org.junit.jupiter.engine")).build();

		// @formatter:off
		TestDescriptor engineDescriptor = engineDescriptor()
				.with(
						classTestDescriptor("matching", MatchingClass.class)
				)
				.build();
		// @formatter:on

		applier.applyAllFilters(request, engineDescriptor);

		assertThat(engineDescriptor.getDescendants()).isEmpty();
	}

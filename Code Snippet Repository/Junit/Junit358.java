	@Test
	void packageNameFilterInclude_nonMatchingPackagesAreExcluded() {

		EngineDiscoveryRequest request = request().filters(
			PackageNameFilter.includePackageNames("org.junit.jupiter.engine.unknown")).build();

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

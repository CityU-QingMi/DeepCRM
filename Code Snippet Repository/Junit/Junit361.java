	@Test
	void packageNameFilterExclude_nonMatchingPackagesAreIncluded() {

		EngineDiscoveryRequest request = request().filters(
			PackageNameFilter.excludePackageNames("org.junit.jupiter.engine.unknown")).build();

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

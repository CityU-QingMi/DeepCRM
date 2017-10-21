	private Set<Description> determineDescendants(Optional<? extends TestDescriptor> identifiedTestDescriptor) {
		// @formatter:off
		return identifiedTestDescriptor.map(
				testDescriptor -> testDescriptor
						.getDescendants()
						.stream()
						.map(VintageTestDescriptor.class::cast)
						.map(VintageTestDescriptor::getDescription)
						.collect(toSet()))
				.orElseGet(Collections::emptySet);
		// @formatter:on
	}

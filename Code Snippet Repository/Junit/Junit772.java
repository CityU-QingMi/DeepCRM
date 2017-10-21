	Optional<VintageTestDescriptor> lookupTestDescriptor(Description description) {
		List<VintageTestDescriptor> descriptors = descriptionToDescriptors.get(description);
		if (descriptors == null) {
			return Optional.empty();
		}
		if (descriptors.size() == 1) {
			return Optional.of(getOnlyElement(descriptors));
		}
		// @formatter:off
		return descriptors.stream()
				.filter(testDescriptor -> description == testDescriptor.getDescription())
				.findFirst();
		// @formatter:on
	}

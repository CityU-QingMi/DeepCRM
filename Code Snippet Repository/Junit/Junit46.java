	private boolean includePackage(ClassTestDescriptor classTestDescriptor,
			List<PackageNameFilter> packageNameFilters) {

		// Nested Tests are never filtered out
		if (classTestDescriptor instanceof NestedClassTestDescriptor) {
			return true;
		}

		Class<?> testClass = classTestDescriptor.getTestClass();

		// @formatter:off
		return (packageNameFilters.stream()
				.map(filter -> filter.apply(testClass.getPackage().getName()))
				.noneMatch(FilterResult::excluded));
		// @formatter:on
	}

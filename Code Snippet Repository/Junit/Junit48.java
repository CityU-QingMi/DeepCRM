	private boolean includeClass(ClassTestDescriptor classTestDescriptor, List<ClassNameFilter> classNameFilters) {

		// Nested Tests are never filtered out
		if (classTestDescriptor instanceof NestedClassTestDescriptor) {
			return true;
		}

		Class<?> testClass = classTestDescriptor.getTestClass();

		// @formatter:off
		return classNameFilters.stream()
				.map(filter -> filter.apply(testClass.getName()))
				.noneMatch(FilterResult::excluded);
		// @formatter:on
	}

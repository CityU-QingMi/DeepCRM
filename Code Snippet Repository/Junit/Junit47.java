	private void applyClassNameFilters(List<ClassNameFilter> classNameFilters, TestDescriptor engineDescriptor) {
		if (classNameFilters.isEmpty()) {
			return;
		}
		TestDescriptor.Visitor filteringVisitor = descriptor -> {
			if (descriptor instanceof ClassTestDescriptor
					&& !includeClass((ClassTestDescriptor) descriptor, classNameFilters)) {
				descriptor.removeFromHierarchy();
			}
		};
		engineDescriptor.accept(filteringVisitor);
	}

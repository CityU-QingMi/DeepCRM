	private void applyPackageNameFilters(List<PackageNameFilter> packageNameFilters, TestDescriptor engineDescriptor) {
		if (packageNameFilters.isEmpty()) {
			return;
		}
		TestDescriptor.Visitor filteringVisitor = descriptor -> {
			if (descriptor instanceof ClassTestDescriptor) {
				if (!includePackage((ClassTestDescriptor) descriptor, packageNameFilters)) {
					descriptor.removeFromHierarchy();
				}
			}
		};
		engineDescriptor.accept(filteringVisitor);
	}

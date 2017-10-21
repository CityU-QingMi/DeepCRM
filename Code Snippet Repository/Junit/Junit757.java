	@Override
	public void resolve(EngineDiscoveryRequest request, ClassFilter classFilter, TestClassCollector collector) {
		// @formatter:off
		request.getSelectorsByType(ClasspathRootSelector.class)
			.stream()
			.map(ClasspathRootSelector::getClasspathRoot)
			.map(root -> findAllClassesInClasspathRoot(root, classFilter))
			.flatMap(Collection::stream)
			.forEach(collector::addCompletely);
		// @formatter:on
	}

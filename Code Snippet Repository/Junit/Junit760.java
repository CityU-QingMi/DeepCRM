	@Override
	public void resolve(EngineDiscoveryRequest request, ClassFilter classFilter, TestClassCollector collector) {
		// @formatter:off
		request.getSelectorsByType(PackageSelector.class)
			.stream()
			.map(PackageSelector::getPackageName)
			.map(packageName -> findAllClassesInPackage(packageName, classFilter))
			.flatMap(Collection::stream)
			.forEach(collector::addCompletely);
		// @formatter:on
	}

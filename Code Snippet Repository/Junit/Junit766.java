	@Override
	public void resolve(EngineDiscoveryRequest request, ClassFilter classFilter, TestClassCollector collector) {
		// @formatter:off
		request.getSelectorsByType(UniqueIdSelector.class)
			.stream()
			.map(UniqueIdSelector::getUniqueId)
			.filter(this::isNotEngineId)
			.filter(this::isForVintageEngine)
			.forEach(uniqueId -> resolveIntoFilteredTestClass(uniqueId, classFilter, collector));
		// @formatter:on
	}

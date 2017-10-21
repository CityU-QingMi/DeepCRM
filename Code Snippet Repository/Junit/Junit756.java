	@Override
	public void resolve(EngineDiscoveryRequest request, ClassFilter classFilter, TestClassCollector collector) {
		// @formatter:off
		request.getSelectorsByType(ClassSelector.class)
			.stream()
			.map(ClassSelector::getJavaClass)
			.filter(classFilter)
			.forEach(collector::addCompletely);
		// @formatter:on
	}

	@SuppressWarnings("")
	private String createDiscoveredTestEnginesMessage(Iterable<TestEngine> testEngines) {
		// @formatter:off
		List<String> details = ((Stream<TestEngine>) CollectionUtils.toStream(testEngines))
				.map(engine -> String.format("%s (%s)", engine.getId(), join(", ", computeAttributes(engine))))
				.collect(toList());
		return details.isEmpty()
				? "No TestEngine implementation discovered."
				: "Discovered TestEngines with IDs: [" + join(", ", details) + "]";
		// @formatter:on
	}

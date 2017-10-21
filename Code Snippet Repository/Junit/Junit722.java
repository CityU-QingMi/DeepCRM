	private LauncherDiscoveryRequest createDiscoveryRequest() {
		List<DiscoverySelector> selectors = getSelectorsFromAnnotations();

		// Allows to simply add @RunWith(JUnitPlatform.class) to any test case
		boolean isSuite = !selectors.isEmpty();
		if (!isSuite) {
			selectors.add(selectClass(this.testClass));
		}

		LauncherDiscoveryRequestBuilder requestBuilder = request().selectors(selectors);
		addFiltersFromAnnotations(requestBuilder, isSuite);
		return requestBuilder.build();
	}

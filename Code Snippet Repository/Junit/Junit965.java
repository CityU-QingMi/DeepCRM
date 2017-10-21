	@Test
	void convertsTagOptions() {
		options.setScanClasspath(true);
		options.setIncludedTags(asList("fast", "medium", "slow"));
		options.setExcludedTags(asList("slow"));

		LauncherDiscoveryRequest request = convert();
		List<PostDiscoveryFilter> postDiscoveryFilters = request.getPostDiscoveryFilters();

		assertThat(postDiscoveryFilters).hasSize(2);
		assertThat(postDiscoveryFilters.get(0).toString()).contains("TagFilter");
		assertThat(postDiscoveryFilters.get(1).toString()).contains("TagFilter");
	}

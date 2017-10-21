	private void addFiltersFromAnnotations(LauncherDiscoveryRequestBuilder requestBuilder, boolean isSuite) {
		addIncludeClassNamePatternFilter(requestBuilder, isSuite);
		addExcludeClassNamePatternFilter(requestBuilder);

		addIncludePackagesFilter(requestBuilder);
		addExcludePackagesFilter(requestBuilder);

		addIncludedTagsFilter(requestBuilder);
		addExcludedTagsFilter(requestBuilder);

		addIncludedEnginesFilter(requestBuilder);
		addExcludedEnginesFilter(requestBuilder);
	}

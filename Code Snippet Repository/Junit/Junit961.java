	@Test
	void convertsDefaultIncludeClassNamePatternOption() {
		options.setScanClasspath(true);

		LauncherDiscoveryRequest request = convert();

		List<ClassNameFilter> filter = request.getFiltersByType(ClassNameFilter.class);
		assertThat(filter).hasSize(1);
		assertThat(filter.get(0).toString()).contains(STANDARD_INCLUDE_PATTERN);
	}

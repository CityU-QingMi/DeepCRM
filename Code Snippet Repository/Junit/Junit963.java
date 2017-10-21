	@Test
	void convertsExcludeClassNamePatternOption() {
		options.setScanClasspath(true);
		options.setExcludedClassNamePatterns(asList("Foo.*Bar", "Bar.*Foo"));

		LauncherDiscoveryRequest request = convert();

		List<ClassNameFilter> filter = request.getFiltersByType(ClassNameFilter.class);
		assertThat(filter).hasSize(2);
		assertThat(filter.get(1).toString()).contains("Foo.*Bar");
		assertThat(filter.get(1).toString()).contains("Bar.*Foo");
	}

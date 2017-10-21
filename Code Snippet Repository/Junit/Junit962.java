	@Test
	void convertsExplicitIncludeClassNamePatternOption() {
		options.setScanClasspath(true);
		options.setIncludedClassNamePatterns(asList("Foo.*Bar", "Bar.*Foo"));

		LauncherDiscoveryRequest request = convert();

		List<ClassNameFilter> filter = request.getFiltersByType(ClassNameFilter.class);
		assertThat(filter).hasSize(1);
		assertThat(filter.get(0).toString()).contains("Foo.*Bar");
		assertThat(filter.get(0).toString()).contains("Bar.*Foo");
	}

	@Test
	void convertsClasspathResourceSelectors() {
		options.setSelectedClasspathResources(asList("foo.csv", "com/example/bar.json"));

		LauncherDiscoveryRequest request = convert();
		List<ClasspathResourceSelector> classpathResourceSelectors = request.getSelectorsByType(
			ClasspathResourceSelector.class);

		assertThat(classpathResourceSelectors).extracting(
			ClasspathResourceSelector::getClasspathResourceName).containsExactly("foo.csv", "com/example/bar.json");
	}

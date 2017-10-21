	@Test
	void convertsScanClasspathOptionWithExplicitRootDirectories() {
		options.setScanClasspath(true);
		options.setSelectedClasspathEntries(asList(Paths.get("."), Paths.get("..")));

		LauncherDiscoveryRequest request = convert();

		List<ClasspathRootSelector> classpathRootSelectors = request.getSelectorsByType(ClasspathRootSelector.class);
		// @formatter:off
		assertThat(classpathRootSelectors).extracting(ClasspathRootSelector::getClasspathRoot)
				.containsExactly(new File(".").toURI(), new File("..").toURI());
		// @formatter:on
	}

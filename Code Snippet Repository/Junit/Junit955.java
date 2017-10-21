	@Test
	void convertsClassSelectors() {
		options.setSelectedClasses(asList("com.acme.Foo", "com.example.Bar"));

		LauncherDiscoveryRequest request = convert();
		List<ClassSelector> classSelectors = request.getSelectorsByType(ClassSelector.class);

		assertThat(classSelectors).extracting(ClassSelector::getClassName).containsExactly("com.acme.Foo",
			"com.example.Bar");
	}

	@Test
	void convertsPackageSelectors() {
		options.setSelectedPackages(asList("com.acme.foo", "com.example.bar"));

		LauncherDiscoveryRequest request = convert();
		List<PackageSelector> packageSelectors = request.getSelectorsByType(PackageSelector.class);

		assertThat(packageSelectors).extracting(PackageSelector::getPackageName).containsExactly("com.acme.foo",
			"com.example.bar");
	}

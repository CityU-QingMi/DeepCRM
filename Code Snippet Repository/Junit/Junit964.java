	@Test
	void convertsPackageOptions() {
		options.setScanClasspath(true);
		options.setIncludedPackages(asList("org.junit.included1", "org.junit.included2", "org.junit.included3"));
		options.setExcludedPackages(asList("org.junit.excluded1"));

		LauncherDiscoveryRequest request = convert();
		List<PackageNameFilter> packageNameFilters = request.getFiltersByType(PackageNameFilter.class);

		assertThat(packageNameFilters).hasSize(2);
		assertThat(packageNameFilters.get(0).toString()).contains("org.junit.included1");
		assertThat(packageNameFilters.get(0).toString()).contains("org.junit.included2");
		assertThat(packageNameFilters.get(0).toString()).contains("org.junit.included3");
		assertThat(packageNameFilters.get(1).toString()).contains("org.junit.excluded1");
	}

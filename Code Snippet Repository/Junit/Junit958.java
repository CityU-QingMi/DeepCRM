	@Test
	void convertsConfigurationParameters() {
		options.setScanClasspath(true);
		options.setConfigurationParameters(mapOf(entry("foo", "bar"), entry("baz", "true")));

		LauncherDiscoveryRequest request = convert();
		ConfigurationParameters configurationParameters = request.getConfigurationParameters();

		assertThat(configurationParameters.size()).isEqualTo(2);
		assertThat(configurationParameters.get("foo")).contains("bar");
		assertThat(configurationParameters.getBoolean("baz")).contains(true);
	}

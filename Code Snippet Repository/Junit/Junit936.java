	@Test
	void parseValidConfigurationParameters() {
		// @formatter:off
		assertAll(
				() -> assertThat(parseArgLine("-config foo=bar").getConfigurationParameters())
						.containsOnly(entry("foo", "bar")),
				() -> assertThat(parseArgLine("-config=foo=bar").getConfigurationParameters())
						.containsOnly(entry("foo", "bar")),
				() -> assertThat(parseArgLine("--config foo=bar").getConfigurationParameters())
						.containsOnly(entry("foo", "bar")),
				() -> assertThat(parseArgLine("--config=foo=bar").getConfigurationParameters())
						.containsOnly(entry("foo", "bar")),
				() -> assertThat(parseArgLine("--config foo=bar --config baz=qux").getConfigurationParameters())
						.containsExactly(entry("foo", "bar"), entry("baz", "qux"))
		);
		// @formatter:on
	}

		@Test
		void configurationParameterAddedByMap_isStoredInDiscoveryRequest() {
			// @formatter:off
			LauncherDiscoveryRequest discoveryRequest = request()
					.configurationParameters(singletonMap("key", "value"))
					.build();
			// @formatter:on

			ConfigurationParameters configParams = discoveryRequest.getConfigurationParameters();
			assertThat(configParams.get("key")).contains("value");
		}

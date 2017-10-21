		@Test
		void configurationParameterAddedDirectly_isStoredInDiscoveryRequest() {
			// @formatter:off
			LauncherDiscoveryRequest discoveryRequest = request()
					.configurationParameter("key", "value")
					.build();
			// @formatter:on

			ConfigurationParameters configParams = discoveryRequest.getConfigurationParameters();
			assertThat(configParams.get("key")).contains("value");
		}

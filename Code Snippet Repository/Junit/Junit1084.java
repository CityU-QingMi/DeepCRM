		@Test
		void multipleConfigurationParametersAddedDirectly_areStoredInDiscoveryRequest() {
			// @formatter:off
			LauncherDiscoveryRequest discoveryRequest = request()
					.configurationParameter("key1", "value1")
					.configurationParameter("key2", "value2")
					.build();
			// @formatter:on

			ConfigurationParameters configParams = discoveryRequest.getConfigurationParameters();
			assertThat(configParams.get("key1")).contains("value1");
			assertThat(configParams.get("key2")).contains("value2");
		}

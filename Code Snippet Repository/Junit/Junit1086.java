		@Test
		void multipleConfigurationParametersAddedByMap_areStoredInDiscoveryRequest() throws Exception {
			Map<String, String> configurationParams = new HashMap<>();
			configurationParams.put("key1", "value1");
			configurationParams.put("key2", "value2");

			// @formatter:off
			LauncherDiscoveryRequest discoveryRequest = request()
					.configurationParameters(configurationParams)
					.build();
			// @formatter:on

			ConfigurationParameters configParams = discoveryRequest.getConfigurationParameters();
			assertThat(configParams.get("key1")).contains("value1");
			assertThat(configParams.get("key2")).contains("value2");
		}

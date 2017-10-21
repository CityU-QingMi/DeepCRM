	@Test
	public void defaultSettingsOfHttpClientMergedOnExecutorCustomization() throws Exception {
		RequestConfig defaultConfig = RequestConfig.custom().setConnectTimeout(1234).build();
		CloseableHttpClient client = mock(CloseableHttpClient.class,
				withSettings().extraInterfaces(Configurable.class));
		Configurable configurable = (Configurable) client;
		when(configurable.getConfig()).thenReturn(defaultConfig);

		HttpComponentsClientHttpRequestFactory hrf = new HttpComponentsClientHttpRequestFactory(client);
		assertSame("Default client configuration is expected", defaultConfig, retrieveRequestConfig(hrf));

		hrf.setConnectionRequestTimeout(4567);
		RequestConfig requestConfig = retrieveRequestConfig(hrf);
		assertNotNull(requestConfig);
		assertEquals(4567, requestConfig.getConnectionRequestTimeout());
		// Default connection timeout merged
		assertEquals(1234, requestConfig.getConnectTimeout());
	}

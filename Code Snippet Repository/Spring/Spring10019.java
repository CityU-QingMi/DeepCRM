	@Test
	public void localSettingsOverrideClientDefaultSettings() throws Exception {
		RequestConfig defaultConfig = RequestConfig.custom()
				.setConnectTimeout(1234).setConnectionRequestTimeout(6789).build();
		CloseableHttpClient client = mock(CloseableHttpClient.class,
				withSettings().extraInterfaces(Configurable.class));
		Configurable configurable = (Configurable) client;
		when(configurable.getConfig()).thenReturn(defaultConfig);

		HttpComponentsClientHttpRequestFactory hrf = new HttpComponentsClientHttpRequestFactory(client);
		hrf.setConnectTimeout(5000);

		RequestConfig requestConfig = retrieveRequestConfig(hrf);
		assertEquals(5000, requestConfig.getConnectTimeout());
		assertEquals(6789, requestConfig.getConnectionRequestTimeout());
		assertEquals(-1, requestConfig.getSocketTimeout());
	}

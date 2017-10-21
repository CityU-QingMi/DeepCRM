	@Test
	public void mergeBasedOnCurrentHttpClient() throws Exception {
		RequestConfig defaultConfig = RequestConfig.custom()
				.setSocketTimeout(1234).build();
		final CloseableHttpClient client = mock(CloseableHttpClient.class,
				withSettings().extraInterfaces(Configurable.class));
		Configurable configurable = (Configurable) client;
		when(configurable.getConfig()).thenReturn(defaultConfig);

		HttpComponentsClientHttpRequestFactory hrf = new HttpComponentsClientHttpRequestFactory() {
			@Override
			public HttpClient getHttpClient() {
				return client;
			}
		};
		hrf.setReadTimeout(5000);

		RequestConfig requestConfig = retrieveRequestConfig(hrf);
		assertEquals(-1, requestConfig.getConnectTimeout());
		assertEquals(-1, requestConfig.getConnectionRequestTimeout());
		assertEquals(5000, requestConfig.getSocketTimeout());

		// Update the Http client so that it returns an updated  config
		RequestConfig updatedDefaultConfig = RequestConfig.custom()
				.setConnectTimeout(1234).build();
		when(configurable.getConfig()).thenReturn(updatedDefaultConfig);
		hrf.setReadTimeout(7000);
		RequestConfig requestConfig2 = retrieveRequestConfig(hrf);
		assertEquals(1234, requestConfig2.getConnectTimeout());
		assertEquals(-1, requestConfig2.getConnectionRequestTimeout());
		assertEquals(7000, requestConfig2.getSocketTimeout());
	}

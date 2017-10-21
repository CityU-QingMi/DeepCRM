	@Test
	public void defaultSettingsOfHttpClientMergedOnExecutorCustomization() throws IOException {
		RequestConfig defaultConfig = RequestConfig.custom().setConnectTimeout(1234).build();
		CloseableHttpClient client = mock(CloseableHttpClient.class,
				withSettings().extraInterfaces(Configurable.class));
		Configurable configurable = (Configurable) client;
		when(configurable.getConfig()).thenReturn(defaultConfig);

		HttpComponentsHttpInvokerRequestExecutor executor =
				new HttpComponentsHttpInvokerRequestExecutor(client);
		HttpInvokerClientConfiguration config = mockHttpInvokerClientConfiguration("http://fake-service");
		HttpPost httpPost = executor.createHttpPost(config);
		assertSame("Default client configuration is expected", defaultConfig, httpPost.getConfig());

		executor.setConnectionRequestTimeout(4567);
		HttpPost httpPost2 = executor.createHttpPost(config);
		assertNotNull(httpPost2.getConfig());
		assertEquals(4567, httpPost2.getConfig().getConnectionRequestTimeout());
		// Default connection timeout merged
		assertEquals(1234, httpPost2.getConfig().getConnectTimeout());
	}

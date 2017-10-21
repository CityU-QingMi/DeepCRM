	@Test
	public void localSettingsOverrideClientDefaultSettings() throws Exception {
		RequestConfig defaultConfig = RequestConfig.custom()
				.setConnectTimeout(1234).setConnectionRequestTimeout(6789).build();
		CloseableHttpClient client = mock(CloseableHttpClient.class,
				withSettings().extraInterfaces(Configurable.class));
		Configurable configurable = (Configurable) client;
		when(configurable.getConfig()).thenReturn(defaultConfig);

		HttpComponentsHttpInvokerRequestExecutor executor =
				new HttpComponentsHttpInvokerRequestExecutor(client);
		executor.setConnectTimeout(5000);

		HttpInvokerClientConfiguration config = mockHttpInvokerClientConfiguration("http://fake-service");
		HttpPost httpPost = executor.createHttpPost(config);
		RequestConfig requestConfig = httpPost.getConfig();
		assertEquals(5000, requestConfig.getConnectTimeout());
		assertEquals(6789, requestConfig.getConnectionRequestTimeout());
		assertEquals(-1, requestConfig.getSocketTimeout());
	}

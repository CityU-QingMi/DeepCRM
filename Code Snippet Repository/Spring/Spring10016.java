	@Test
	public void defaultSettingsOfHttpAsyncClientLostOnExecutorCustomization() throws Exception {
		CloseableHttpAsyncClient client = HttpAsyncClientBuilder.create()
				.setDefaultRequestConfig(RequestConfig.custom().setConnectTimeout(1234).build())
				.build();
		HttpComponentsAsyncClientHttpRequestFactory factory = new HttpComponentsAsyncClientHttpRequestFactory(client);

		URI uri = new URI(baseUrl + "/status/ok");
		HttpComponentsAsyncClientHttpRequest request = (HttpComponentsAsyncClientHttpRequest)
				factory.createAsyncRequest(uri, HttpMethod.GET);

		assertNull("No custom config should be set with a custom HttpClient",
				request.getHttpContext().getAttribute(HttpClientContext.REQUEST_CONFIG));

		factory.setConnectionRequestTimeout(4567);
		HttpComponentsAsyncClientHttpRequest request2 = (HttpComponentsAsyncClientHttpRequest)
				factory.createAsyncRequest(uri, HttpMethod.GET);
		Object requestConfigAttribute = request2.getHttpContext().getAttribute(HttpClientContext.REQUEST_CONFIG);
		assertNotNull(requestConfigAttribute);
		RequestConfig requestConfig = (RequestConfig) requestConfigAttribute;

		assertEquals(4567, requestConfig.getConnectionRequestTimeout());
		// No way to access the request config of the HTTP client so no way to "merge" our customizations
		assertEquals(-1, requestConfig.getConnectTimeout());
	}

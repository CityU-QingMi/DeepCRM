	@Test
	public void assertCustomConfig() throws Exception {
		HttpClient httpClient = HttpClientBuilder.create().build();
		HttpComponentsClientHttpRequestFactory hrf = new HttpComponentsClientHttpRequestFactory(httpClient);
		hrf.setConnectTimeout(1234);
		hrf.setConnectionRequestTimeout(4321);
		hrf.setReadTimeout(4567);

		URI uri = new URI(baseUrl + "/status/ok");
		HttpComponentsClientHttpRequest request = (HttpComponentsClientHttpRequest)
				hrf.createRequest(uri, HttpMethod.GET);

		Object config = request.getHttpContext().getAttribute(HttpClientContext.REQUEST_CONFIG);
		assertNotNull("Request config should be set", config);
		assertTrue("Wrong request config type" + config.getClass().getName(),
				RequestConfig.class.isInstance(config));
		RequestConfig requestConfig = (RequestConfig) config;
		assertEquals("Wrong custom connection timeout", 1234, requestConfig.getConnectTimeout());
		assertEquals("Wrong custom connection request timeout", 4321, requestConfig.getConnectionRequestTimeout());
		assertEquals("Wrong custom socket timeout", 4567, requestConfig.getSocketTimeout());
	}

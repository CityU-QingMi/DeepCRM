	@Test
	public void customHttpAsyncClientUsesItsDefault() throws Exception {
		HttpComponentsAsyncClientHttpRequestFactory factory =
				new HttpComponentsAsyncClientHttpRequestFactory();

		URI uri = new URI(baseUrl + "/status/ok");
		HttpComponentsAsyncClientHttpRequest request = (HttpComponentsAsyncClientHttpRequest)
				factory.createAsyncRequest(uri, HttpMethod.GET);

		assertNull("No custom config should be set with a custom HttpAsyncClient",
				request.getHttpContext().getAttribute(HttpClientContext.REQUEST_CONFIG));
	}

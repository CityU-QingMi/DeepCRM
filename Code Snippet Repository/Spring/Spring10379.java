	@Test
	public void ignoreFactorySettings() throws IOException {
		CloseableHttpClient httpClient = HttpClientBuilder.create().build();
		HttpComponentsHttpInvokerRequestExecutor executor = new HttpComponentsHttpInvokerRequestExecutor(httpClient) {
			@Override
			protected RequestConfig createRequestConfig(HttpInvokerClientConfiguration config) {
				return null;
			}
		};

		HttpInvokerClientConfiguration config = mockHttpInvokerClientConfiguration("http://fake-service");
		HttpPost httpPost = executor.createHttpPost(config);
		assertNull("custom request config should not be set", httpPost.getConfig());
	}

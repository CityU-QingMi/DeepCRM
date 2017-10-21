	@Test
	public void changeURI() throws Exception {
		final URI changedUri = new URI("http://example.com/2");

		ClientHttpRequestInterceptor interceptor = new ClientHttpRequestInterceptor() {
			@Override
			public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
					throws IOException {
				return execution.execute(new HttpRequestWrapper(request) {
					@Override
					public URI getURI() {
						return changedUri;
					}

				}, body);
			}
		};

		requestFactoryMock = new RequestFactoryMock() {
			@Override
			public ClientHttpRequest createRequest(URI uri, HttpMethod httpMethod) throws IOException {
				assertEquals(changedUri, uri);
				return super.createRequest(uri, httpMethod);
			}
		};

		requestFactory =
				new InterceptingClientHttpRequestFactory(requestFactoryMock, Collections.singletonList(interceptor));

		ClientHttpRequest request = requestFactory.createRequest(new URI("http://example.com"), HttpMethod.GET);
		request.execute();
	}

	@Test
	public void changeMethod() throws Exception {
		final HttpMethod changedMethod = HttpMethod.POST;

		ClientHttpRequestInterceptor interceptor = new ClientHttpRequestInterceptor() {
			@Override
			public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
					throws IOException {
				return execution.execute(new HttpRequestWrapper(request) {
					@Override
					public HttpMethod getMethod() {
						return changedMethod;
					}

				}, body);
			}
		};

		requestFactoryMock = new RequestFactoryMock() {
			@Override
			public ClientHttpRequest createRequest(URI uri, HttpMethod httpMethod) throws IOException {
				assertEquals(changedMethod, httpMethod);
				return super.createRequest(uri, httpMethod);
			}
		};

		requestFactory =
				new InterceptingClientHttpRequestFactory(requestFactoryMock, Collections.singletonList(interceptor));

		ClientHttpRequest request = requestFactory.createRequest(new URI("http://example.com"), HttpMethod.GET);
		request.execute();
	}

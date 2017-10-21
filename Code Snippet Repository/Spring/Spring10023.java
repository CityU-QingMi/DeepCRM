	@Test
	public void noExecution() throws Exception {
		List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
		interceptors.add(new ClientHttpRequestInterceptor() {
			@Override
			public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
					throws IOException {
				return responseMock;
			}
		});

		interceptors.add(new NoOpInterceptor());
		requestFactory = new InterceptingClientHttpRequestFactory(requestFactoryMock, interceptors);

		ClientHttpRequest request = requestFactory.createRequest(new URI("http://example.com"), HttpMethod.GET);
		ClientHttpResponse response = request.execute();

		assertFalse(((NoOpInterceptor) interceptors.get(1)).invoked);
		assertFalse(requestMock.executed);
		assertSame(responseMock, response);
	}

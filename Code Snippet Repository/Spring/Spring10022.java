	@Test
	public void basic() throws Exception {
		List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
		interceptors.add(new NoOpInterceptor());
		interceptors.add(new NoOpInterceptor());
		interceptors.add(new NoOpInterceptor());
		requestFactory = new InterceptingClientHttpRequestFactory(requestFactoryMock, interceptors);

		ClientHttpRequest request = requestFactory.createRequest(new URI("http://example.com"), HttpMethod.GET);
		ClientHttpResponse response = request.execute();

		assertTrue(((NoOpInterceptor) interceptors.get(0)).invoked);
		assertTrue(((NoOpInterceptor) interceptors.get(1)).invoked);
		assertTrue(((NoOpInterceptor) interceptors.get(2)).invoked);
		assertTrue(requestMock.executed);
		assertSame(responseMock, response);
	}

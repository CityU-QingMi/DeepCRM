	@Test
	public void changeHeaders() throws Exception {
		final String headerName = "Foo";
		final String headerValue = "Bar";
		final String otherValue = "Baz";

		ClientHttpRequestInterceptor interceptor = new ClientHttpRequestInterceptor() {
			@Override
			public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
					throws IOException {
				HttpRequestWrapper wrapper = new HttpRequestWrapper(request);
				wrapper.getHeaders().add(headerName, otherValue);
				return execution.execute(wrapper, body);
			}
		};

		requestMock = new RequestMock() {
			@Override
			public ClientHttpResponse execute() throws IOException {
				List<String> headerValues = getHeaders().get(headerName);
				assertEquals(2, headerValues.size());
				assertEquals(headerValue, headerValues.get(0));
				assertEquals(otherValue, headerValues.get(1));
				return super.execute();
			}
		};
		requestMock.getHeaders().add(headerName, headerValue);

		requestFactory =
				new InterceptingClientHttpRequestFactory(requestFactoryMock, Collections.singletonList(interceptor));

		ClientHttpRequest request = requestFactory.createRequest(new URI("http://example.com"), HttpMethod.GET);
		request.execute();
	}

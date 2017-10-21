	@Test
	public void changeBody() throws Exception {
		final byte[] changedBody = "Foo".getBytes();

		ClientHttpRequestInterceptor interceptor = new ClientHttpRequestInterceptor() {
			@Override
			public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
					throws IOException {
				return execution.execute(request, changedBody);
			}
		};

		requestFactory =
				new InterceptingClientHttpRequestFactory(requestFactoryMock, Collections.singletonList(interceptor));

		ClientHttpRequest request = requestFactory.createRequest(new URI("http://example.com"), HttpMethod.GET);
		request.execute();
		assertTrue(Arrays.equals(changedBody, requestMock.body.toByteArray()));
	}

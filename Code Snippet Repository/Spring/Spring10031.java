	@Test
	public void interceptor() throws Exception {
		final String headerName = "MyHeader";
		final String headerValue = "MyValue";
		ClientHttpRequestInterceptor interceptor = new ClientHttpRequestInterceptor() {
			@Override
			public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
					throws IOException {
				request.getHeaders().add(headerName, headerValue);
				return execution.execute(request, body);
			}
		};
		InterceptingClientHttpRequestFactory factory = new InterceptingClientHttpRequestFactory(createRequestFactory(),
				Collections.singletonList(interceptor));

		ClientHttpResponse response = null;
		try {
			ClientHttpRequest request = factory.createRequest(new URI(baseUrl + "/echo"), HttpMethod.GET);
			response = request.execute();
			assertEquals("Invalid response status", HttpStatus.OK, response.getStatusCode());
			HttpHeaders responseHeaders = response.getHeaders();
			assertEquals("Custom header invalid", headerValue, responseHeaders.getFirst(headerName));
		}
		finally {
			if (response != null) {
				response.close();
			}
		}
	}

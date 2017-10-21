		private org.springframework.mock.http.client.MockAsyncClientHttpRequest createRequestInternal(URI uri, HttpMethod method) {
			Assert.notNull(uri, "'uri' must not be null");
			Assert.notNull(method, "'httpMethod' must not be null");

			return new org.springframework.mock.http.client.MockAsyncClientHttpRequest(method, uri) {

				@Override
				protected ClientHttpResponse executeInternal() throws IOException {
					ClientHttpResponse response = expectationManager.validateRequest(this);
					setResponse(response);
					return response;
				}
			};
		}

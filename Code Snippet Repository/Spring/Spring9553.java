		@Override
		public void doWithRequest(final org.springframework.http.client.AsyncClientHttpRequest request) throws IOException {
			this.adaptee.doWithRequest(new ClientHttpRequest() {
				@Override
				public ClientHttpResponse execute() throws IOException {
					throw new UnsupportedOperationException("execute not supported");
				}
				@Override
				public OutputStream getBody() throws IOException {
					return request.getBody();
				}
				@Override
				@Nullable
				public HttpMethod getMethod() {
					return request.getMethod();
				}
				@Override
				public String getMethodValue() {
					return request.getMethodValue();
				}
				@Override
				public URI getURI() {
					return request.getURI();
				}
				@Override
				public HttpHeaders getHeaders() {
					return request.getHeaders();
				}
			});
		}

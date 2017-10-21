		@Override
		public MockRestServiceServer build(RequestExpectationManager manager) {
			MockRestServiceServer server = new MockRestServiceServer(manager);
			MockClientHttpRequestFactory factory = server.new MockClientHttpRequestFactory();
			if (this.restTemplate != null) {
				this.restTemplate.setRequestFactory(factory);
			}
			if (this.asyncRestTemplate != null) {
				this.asyncRestTemplate.setAsyncRequestFactory(factory);
			}
			return server;
		}

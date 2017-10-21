		@Override
		public Mono<ClientResponse> exchange(ClientRequest request) {
			Assert.notNull(request, "'request' must not be null");
			return this.connector
					.connect(request.method(), request.url(),
							clientHttpRequest -> request.writeTo(clientHttpRequest, this.strategies))
					.log("org.springframework.web.reactive.function.client", Level.FINE)
					.map(clientHttpResponse -> new DefaultClientResponse(clientHttpResponse,
							this.strategies));
		}

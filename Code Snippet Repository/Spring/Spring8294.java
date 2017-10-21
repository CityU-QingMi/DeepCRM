	@Override
	public Mono<ClientHttpResponse> connect(HttpMethod httpMethod, URI uri,
			Function<? super ClientHttpRequest, Mono<Void>> requestCallback) {

		MonoProcessor<ClientHttpResponse> result = MonoProcessor.create();

		MockClientHttpRequest mockClientRequest = new MockClientHttpRequest(httpMethod, uri);
		MockServerHttpResponse mockServerResponse = new MockServerHttpResponse();

		mockClientRequest.setWriteHandler(requestBody -> {
			log("Invoking HttpHandler for ", httpMethod, uri);
			ServerHttpRequest mockServerRequest = adaptRequest(mockClientRequest, requestBody);
			ServerHttpResponse responseToUse = prepareResponse(mockServerResponse, mockServerRequest);
			this.handler.handle(mockServerRequest, responseToUse).subscribe(aVoid -> {}, result::onError);
			return Mono.empty();
		});

		mockServerResponse.setWriteHandler(responseBody -> {
			log("Creating client response for ", httpMethod, uri);
			result.onNext(adaptResponse(mockServerResponse, responseBody));
			return Mono.empty();
		});

		log("Writing client request for ", httpMethod, uri);
		requestCallback.apply(mockClientRequest).subscribe(aVoid -> {}, result::onError);

		return result;
	}

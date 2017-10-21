	@Override
	public Mono<Void> apply(HttpServerRequest request, HttpServerResponse response) {

		NettyDataBufferFactory bufferFactory = new NettyDataBufferFactory(response.alloc());
		ServerHttpRequest adaptedRequest;
		ServerHttpResponse adaptedResponse;
		try {
			adaptedRequest = new ReactorServerHttpRequest(request, bufferFactory);
			adaptedResponse = new ReactorServerHttpResponse(response, bufferFactory);
		}
		catch (URISyntaxException ex) {
			logger.error("Invalid URL " + ex.getMessage(), ex);
			response.status(HttpResponseStatus.BAD_REQUEST);
			return Mono.empty();
		}

		if (HttpMethod.HEAD.equals(adaptedRequest.getMethod())) {
			adaptedResponse = new HttpHeadResponseDecorator(adaptedResponse);
		}

		return this.httpHandler.handle(adaptedRequest, adaptedResponse)
				.onErrorResume(ex -> {
					logger.error("Could not complete request", ex);
					response.status(HttpResponseStatus.INTERNAL_SERVER_ERROR);
					return Mono.empty();
				})
				.doOnSuccess(aVoid -> logger.debug("Successfully completed request"));
	}

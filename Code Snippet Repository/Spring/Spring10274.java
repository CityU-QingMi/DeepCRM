	@Override
	public Observable<Void> handle(HttpServerRequest<ByteBuf> nativeRequest,
			HttpServerResponse<ByteBuf> nativeResponse) {

		Channel channel = nativeResponse.unsafeNettyChannel();
		NettyDataBufferFactory bufferFactory = new NettyDataBufferFactory(channel.alloc());
		InetSocketAddress remoteAddress = (InetSocketAddress) channel.remoteAddress();

		ServerHttpRequest request;
		ServerHttpResponse response;
		try {
			request = new RxNettyServerHttpRequest(nativeRequest, bufferFactory, remoteAddress);
			response = new RxNettyServerHttpResponse(nativeResponse, bufferFactory);
		}
		catch (URISyntaxException ex) {
			logger.error("Could not complete request", ex);
			nativeResponse.setStatus(HttpResponseStatus.BAD_REQUEST);
			return Observable.empty();
		}

		if (HttpMethod.HEAD.equals(request.getMethod())) {
			response = new HttpHeadResponseDecorator(response);
		}

		Publisher<Void> result = this.httpHandler.handle(request, response)
				.onErrorResume(ex -> {
					logger.error("Could not complete request", ex);
					nativeResponse.setStatus(HttpResponseStatus.INTERNAL_SERVER_ERROR);
					return Mono.empty();
				})
				.doOnSuccess(aVoid -> logger.debug("Successfully completed request"));

		return RxReactiveStreams.toObservable(result);
	}

	private Mono<Void> executeInternal(URI url, HttpHeaders requestHeaders, WebSocketHandler handler) {
		MonoProcessor<Void> completionMono = MonoProcessor.create();
		return Mono.fromCallable(
				() -> {
					List<String> protocols = beforeHandshake(url, requestHeaders, handler);
					DefaultConfigurator configurator = new DefaultConfigurator(requestHeaders);
					Endpoint endpoint = createEndpoint(url, handler, completionMono, configurator);
					ClientEndpointConfig config = createEndpointConfig(configurator, protocols);
					return this.webSocketContainer.connectToServer(endpoint, config, url);
				})
				.subscribeOn(Schedulers.elastic()) // connectToServer is blocking
				.then(completionMono);
	}

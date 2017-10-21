	private Mono<Void> executeInternal(URI url, HttpHeaders headers, WebSocketHandler handler) {
		MonoProcessor<Void> completion = MonoProcessor.create();
		return Mono.fromCallable(
				() -> {
					ConnectionBuilder builder = createConnectionBuilder(url);
					List<String> protocols = beforeHandshake(url, headers, handler);
					DefaultNegotiation negotiation = new DefaultNegotiation(protocols, headers, builder);
					builder.setClientNegotiation(negotiation);
					return builder.connect().addNotifier(
							new IoFuture.HandlingNotifier<WebSocketChannel, Object>() {
								@Override
								public void handleDone(WebSocketChannel channel, Object attachment) {
									handleChannel(url, handler, completion, negotiation, channel);
								}
								@Override
								public void handleFailed(IOException ex, Object attachment) {
									completion.onError(new IllegalStateException("Failed to connect", ex));
								}
							}, null);
				})
				.then(completion);
	}

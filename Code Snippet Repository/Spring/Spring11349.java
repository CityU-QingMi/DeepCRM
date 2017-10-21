	private Mono<Void> executeInternal(URI url, HttpHeaders headers, WebSocketHandler handler) {
		MonoProcessor<Void> completionMono = MonoProcessor.create();
		return Mono.fromCallable(
				() -> {
					List<String> protocols = beforeHandshake(url, headers, handler);
					ClientUpgradeRequest upgradeRequest = new ClientUpgradeRequest();
					upgradeRequest.setSubProtocols(protocols);
					Object jettyHandler = createJettyHandler(url, handler, completionMono);
					UpgradeListener upgradeListener = new DefaultUpgradeListener(headers);
					return this.jettyClient.connect(jettyHandler, url, upgradeRequest, upgradeListener);
				})
				.then(completionMono);
	}

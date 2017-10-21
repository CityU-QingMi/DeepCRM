	@Override
	public Mono<Void> send(Publisher<WebSocketMessage> messages) {
		if (this.sendCalled.compareAndSet(false, true)) {
			WebSocketSendProcessor sendProcessor = new WebSocketSendProcessor();
			this.sendProcessor = sendProcessor;
			return Mono.from(subscriber -> {
					messages.subscribe(sendProcessor);
					sendProcessor.subscribe(subscriber);
			});
		}
		else {
			return Mono.error(new IllegalStateException("send() has already been called"));
		}
	}

	@Test
	public void subProtocol() throws Exception {
		String protocol = "echo-v1";
		AtomicReference<HandshakeInfo> infoRef = new AtomicReference<>();
		MonoProcessor<Object> output = MonoProcessor.create();

		client.execute(getUrl("/sub-protocol"),
				new WebSocketHandler() {
					@Override
					public List<String> getSubProtocols() {
						return Collections.singletonList(protocol);
					}
					@Override
					public Mono<Void> handle(WebSocketSession session) {
						infoRef.set(session.getHandshakeInfo());
						return session.receive()
								.map(WebSocketMessage::getPayloadAsText)
								.subscribeWith(output)
								.then();
					}
				})
				.block(Duration.ofMillis(5000));

		HandshakeInfo info = infoRef.get();
		assertThat(info.getHeaders().getFirst("Upgrade"), Matchers.equalToIgnoringCase("websocket"));
		assertEquals(protocol, info.getHeaders().getFirst("Sec-WebSocket-Protocol"));
		assertEquals("Wrong protocol accepted", protocol, info.getSubProtocol());
		assertEquals("Wrong protocol detected on the server side", protocol, output.block(Duration.ofMillis(5000)));
	}

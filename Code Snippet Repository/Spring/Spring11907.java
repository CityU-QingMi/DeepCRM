	@Test
	public void customHeader() throws Exception {
		HttpHeaders headers = new HttpHeaders();
		headers.add("my-header", "my-value");
		MonoProcessor<Object> output = MonoProcessor.create();

		client.execute(getUrl("/custom-header"), headers,
				session -> session.receive()
						.map(WebSocketMessage::getPayloadAsText)
						.subscribeWith(output)
						.then())
				.block(Duration.ofMillis(5000));

		assertEquals("my-header:my-value", output.block(Duration.ofMillis(5000)));
	}

	@Test
	public void sendAndReceive() {
		SubscribableChannel channel = new ExecutorSubscribableChannel(this.executor);
		channel.subscribe(new MessageHandler() {
			@Override
			public void handleMessage(Message<?> message) throws MessagingException {
				MessageChannel replyChannel = (MessageChannel) message.getHeaders().getReplyChannel();
				replyChannel.send(new GenericMessage<>("response"));
			}
		});

		String actual = this.template.convertSendAndReceive(channel, "request", String.class);
		assertEquals("response", actual);
	}

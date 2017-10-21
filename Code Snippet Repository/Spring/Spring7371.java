		@Override
		@SuppressWarnings("")
		public Publisher<Void> apply(NettyInbound inbound, NettyOutbound outbound) {
			DirectProcessor<Void> completion = DirectProcessor.create();
			TcpConnection<P> connection = new ReactorNettyTcpConnection<>(inbound, outbound,  codec, completion);
			scheduler.schedule(() -> connectionHandler.afterConnected(connection));

			inbound.context().addHandler(new StompMessageDecoder<>(codec));

			inbound.receiveObject()
					.cast(Message.class)
					.publishOn(scheduler, PUBLISH_ON_BUFFER_SIZE)
					.subscribe(
							connectionHandler::handleMessage,
							connectionHandler::handleFailure,
							connectionHandler::afterConnectionClosed);

			return completion;
		}

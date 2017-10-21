		@Override
		public void onConnect(WebSocketHttpExchange httpExchange, WebSocketChannel channel) {
			UndertowWebSocketSession session = createSession(channel);
			UndertowWebSocketHandlerAdapter adapter = new UndertowWebSocketHandlerAdapter(session);

			channel.getReceiveSetter().set(adapter);
			channel.resumeReceives();

			this.handler.handle(session).subscribe(session);
		}

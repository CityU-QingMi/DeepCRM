		@Override
		public void handleMessage(WebSocketSession session, WebSocketMessage<?> webSocketMessage) {
			this.lastReadTime = (this.lastReadTime != -1 ? System.currentTimeMillis() : -1);
			List<Message<byte[]>> messages;
			try {
				messages = this.codec.decode(webSocketMessage);
			}
			catch (Throwable ex) {
				this.connectionHandler.handleFailure(ex);
				return;
			}
			for (Message<byte[]> message : messages) {
				this.connectionHandler.handleMessage(message);
			}
		}

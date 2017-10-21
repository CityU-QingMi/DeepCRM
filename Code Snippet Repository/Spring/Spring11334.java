		@Override
		@Nullable
		protected WebSocketMessage read() throws IOException {
			if (this.webSocketMessage != null) {
				WebSocketMessage result = this.webSocketMessage;
				this.webSocketMessage = null;
				resumeReceiving();
				return result;
			}

			return null;
		}

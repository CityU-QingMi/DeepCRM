		@Override
		public void afterConnected(TcpConnection<byte[]> connection) {
			if (logger.isDebugEnabled()) {
				logger.debug("TCP connection opened in session=" + getSessionId());
			}
			this.tcpConnection = connection;
			connection.onReadInactivity(() -> {
				if (this.tcpConnection != null && !this.isStompConnected) {
					handleTcpConnectionFailure("No CONNECTED frame received in " +
							MAX_TIME_TO_CONNECTED_FRAME + " ms.", null);
				}
			}, MAX_TIME_TO_CONNECTED_FRAME);
			connection.send(MessageBuilder.createMessage(EMPTY_PAYLOAD, this.connectHeaders.getMessageHeaders()));
		}

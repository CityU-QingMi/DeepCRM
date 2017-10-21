		private void initHeartbeats(StompHeaderAccessor connectedHeaders) {
			if (this.isRemoteClientSession) {
				return;
			}

			TcpConnection<byte[]> con = this.tcpConnection;
			Assert.state(con != null, "No TcpConnection available");

			long clientSendInterval = this.connectHeaders.getHeartbeat()[0];
			long clientReceiveInterval = this.connectHeaders.getHeartbeat()[1];
			long serverSendInterval = connectedHeaders.getHeartbeat()[0];
			long serverReceiveInterval = connectedHeaders.getHeartbeat()[1];

			if (clientSendInterval > 0 && serverReceiveInterval > 0) {
				long interval = Math.max(clientSendInterval, serverReceiveInterval);
				con.onWriteInactivity(() ->
						con.send(HEARTBEAT_MESSAGE).addCallback(
								result -> {},
								ex -> handleTcpConnectionFailure(
										"Failed to forward heartbeat: " + ex.getMessage(), ex)), interval);
			}
			if (clientReceiveInterval > 0 && serverSendInterval > 0) {
				final long interval = Math.max(clientReceiveInterval, serverSendInterval) * HEARTBEAT_MULTIPLIER;
				con.onReadInactivity(
						() -> handleTcpConnectionFailure("No messages received in " + interval + " ms.", null), interval);
			}
		}

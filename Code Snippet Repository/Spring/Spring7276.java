	private void initHeartbeatTasks(StompHeaders connectedHeaders) {
		long[] connect = this.connectHeaders.getHeartbeat();
		long[] connected = connectedHeaders.getHeartbeat();
		if (connect == null || connected == null) {
			return;
		}
		TcpConnection<byte[]> con = this.connection;
		Assert.state(con != null, "No TcpConnection available");
		if (connect[0] > 0 && connected[1] > 0) {
			long interval = Math.max(connect[0],  connected[1]);
			con.onWriteInactivity(new WriteInactivityTask(), interval);
		}
		if (connect[1] > 0 && connected[0] > 0) {
			long interval = Math.max(connect[1], connected[0]) * HEARTBEAT_MULTIPLIER;
			con.onReadInactivity(new ReadInactivityTask(), interval);
		}
	}

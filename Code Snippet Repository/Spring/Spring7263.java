	private void resetConnection() {
		TcpConnection<?> conn = this.connection;
		this.connection = null;
		if (conn != null) {
			try {
				conn.close();
			}
			catch (Throwable ex) {
				// ignore
			}
		}
	}

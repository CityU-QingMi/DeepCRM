		@Override
		public void run() {
			TcpConnection<byte[]> conn = connection;
			if (conn != null) {
				conn.send(HEARTBEAT).addCallback(
						new ListenableFutureCallback<Void>() {
							public void onSuccess(@Nullable Void result) {
							}
							public void onFailure(Throwable ex) {
								handleFailure(ex);
							}
						});
			}
		}

		public SockJsResponseListener(TransportRequest request, ClientConnection connection, URI url,
				HttpHeaders headers, XhrClientSockJsSession sockJsSession,
				SettableListenableFuture<WebSocketSession> connectFuture) {

			this.request = request;
			this.connection = connection;
			this.url = url;
			this.headers = headers;
			this.session = sockJsSession;
			this.connectFuture = connectFuture;
		}

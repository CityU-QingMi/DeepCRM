		public TestJettyWebSocketServer(final WebSocketHandler webSocketHandler) {

			this.server = new Server();
			ServerConnector connector = new ServerConnector(this.server);
			connector.setPort(0);

			this.server.addConnector(connector);
			this.server.setHandler(new org.eclipse.jetty.websocket.server.WebSocketHandler() {
				@Override
				public void configure(WebSocketServletFactory factory) {
					factory.setCreator(new WebSocketCreator() {
						@Override
						public Object createWebSocket(ServletUpgradeRequest req, ServletUpgradeResponse resp) {
							if (!CollectionUtils.isEmpty(req.getSubProtocols())) {
								resp.setAcceptedSubProtocol(req.getSubProtocols().get(0));
							}
							JettyWebSocketSession session = new JettyWebSocketSession(null, null);
							return new JettyWebSocketHandlerAdapter(webSocketHandler, session);
						}
					});
				}
			});
		}

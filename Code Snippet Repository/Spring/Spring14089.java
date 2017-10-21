		@Override
		protected void configureWebSocketTransport(WebSocketTransportRegistration registry) {
			registry.addDecoratorFactory(new WebSocketHandlerDecoratorFactory() {
				@Override
				public WebSocketHandlerDecorator decorate(WebSocketHandler handler) {
					return new WebSocketHandlerDecorator(handler) {
						@Override
						public void afterConnectionEstablished(WebSocketSession session) throws Exception {
							session.getAttributes().put("decorated", true);
							super.afterConnectionEstablished(session);
						}
					};
				}
			});
		}

		@Override
		public void handleMessage(Message<byte[]> message) {
			StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
			Assert.state(accessor != null, "No StompHeaderAccessor");
			accessor.setSessionId(this.sessionId);
			Principal user = this.connectHeaders.getUser();
			if (user != null) {
				accessor.setUser(user);
			}

			StompCommand command = accessor.getCommand();
			if (StompCommand.CONNECTED.equals(command)) {
				if (logger.isDebugEnabled()) {
					logger.debug("Received " + accessor.getShortLogMessage(EMPTY_PAYLOAD));
				}
				afterStompConnected(accessor);
			}
			else if (logger.isErrorEnabled() && StompCommand.ERROR.equals(command)) {
				logger.error("Received " + accessor.getShortLogMessage(message.getPayload()));
			}
			else if (logger.isTraceEnabled()) {
				logger.trace("Received " + accessor.getDetailedLogMessage(message.getPayload()));
			}

			handleInboundMessage(message);
		}

		@Override
		protected void handleInboundMessage(Message<?> message) {
			StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
			if (accessor != null && StompCommand.MESSAGE.equals(accessor.getCommand())) {
				String destination = accessor.getDestination();
				if (destination == null) {
					if (logger.isDebugEnabled()) {
						logger.debug("Got message on \"system\" connection, with no destination: " +
								accessor.getDetailedLogMessage(message.getPayload()));
					}
					return;
				}
				if (!getSystemSubscriptions().containsKey(destination)) {
					if (logger.isDebugEnabled()) {
						logger.debug("Got message on \"system\" connection with no handler: " +
								accessor.getDetailedLogMessage(message.getPayload()));
					}
					return;
				}
				try {
					MessageHandler handler = getSystemSubscriptions().get(destination);
					handler.handleMessage(message);
				}
				catch (Throwable ex) {
					if (logger.isDebugEnabled()) {
						logger.debug("Error while handling message on \"system\" connection.", ex);
					}
				}
			}
		}

	@Override
	public void handleMessage(Message<?> message) throws MessagingException {
		Message<?> messageToUse = message;
		if (this.broadcastHandler != null) {
			messageToUse = this.broadcastHandler.preHandle(message);
			if (messageToUse == null) {
				return;
			}
		}

		UserDestinationResult result = this.destinationResolver.resolveDestination(messageToUse);
		if (result == null) {
			return;
		}

		if (result.getTargetDestinations().isEmpty()) {
			if (logger.isTraceEnabled()) {
				logger.trace("No active sessions for user destination: " + result.getSourceDestination());
			}
			if (this.broadcastHandler != null) {
				this.broadcastHandler.handleUnresolved(messageToUse);
			}
			return;
		}

		SimpMessageHeaderAccessor accessor = SimpMessageHeaderAccessor.wrap(messageToUse);
		initHeaders(accessor);
		accessor.setNativeHeader(SimpMessageHeaderAccessor.ORIGINAL_DESTINATION, result.getSubscribeDestination());
		accessor.setLeaveMutable(true);

		messageToUse = MessageBuilder.createMessage(messageToUse.getPayload(), accessor.getMessageHeaders());
		if (logger.isTraceEnabled()) {
			logger.trace("Translated " + result.getSourceDestination() + " -> " + result.getTargetDestinations());
		}
		for (String target : result.getTargetDestinations()) {
			this.messagingTemplate.send(target, messageToUse);
		}
	}

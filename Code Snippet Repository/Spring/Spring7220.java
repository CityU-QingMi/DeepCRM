		@Override
		public void afterSendCompletion(
				Message<?> message, MessageChannel channel, boolean sent, @Nullable Exception ex) {

			if (!sent) {
				SimpMessageType messageType = SimpMessageHeaderAccessor.getMessageType(message.getHeaders());
				if (SimpMessageType.DISCONNECT.equals(messageType)) {
					logger.debug("Detected unsent DISCONNECT message. Processing anyway.");
					handleMessage(message);
				}
			}
		}

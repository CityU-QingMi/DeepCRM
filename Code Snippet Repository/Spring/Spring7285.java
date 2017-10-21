		private void sendStompErrorFrameToClient(String errorText) {
			if (this.isRemoteClientSession) {
				StompHeaderAccessor accessor = StompHeaderAccessor.create(StompCommand.ERROR);
				if (getHeaderInitializer() != null) {
					getHeaderInitializer().initHeaders(accessor);
				}
				accessor.setSessionId(this.sessionId);
				Principal user = this.connectHeaders.getUser();
				if (user != null) {
					accessor.setUser(user);
				}
				accessor.setMessage(errorText);
				Message<?> errorMessage = MessageBuilder.createMessage(EMPTY_PAYLOAD, accessor.getMessageHeaders());
				handleInboundMessage(errorMessage);
			}
		}

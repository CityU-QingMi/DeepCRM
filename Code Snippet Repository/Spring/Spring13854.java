	@Override
	public void afterSessionEnded(WebSocketSession session, CloseStatus closeStatus, MessageChannel outputChannel) {
		this.decoders.remove(session.getId());

		Message<byte[]> message = createDisconnectMessage(session);
		SimpAttributes simpAttributes = SimpAttributes.fromMessage(message);
		try {
			SimpAttributesContextHolder.setAttributes(simpAttributes);
			if (this.eventPublisher != null) {
				Principal user = getUser(session);
				publishEvent(this.eventPublisher, new SessionDisconnectEvent(this, message, session.getId(), closeStatus, user));
			}
			outputChannel.send(message);
		}
		finally {
			this.stompAuthentications.remove(session.getId());
			SimpAttributesContextHolder.resetAttributes();
			simpAttributes.sessionCompleted();
		}
	}

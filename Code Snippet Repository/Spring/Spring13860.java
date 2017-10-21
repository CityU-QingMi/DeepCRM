	private StompHeaderAccessor getStompHeaderAccessor(Message<?> message) {
		MessageHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, MessageHeaderAccessor.class);
		if (accessor instanceof StompHeaderAccessor) {
			return (StompHeaderAccessor) accessor;
		}
		else {
			StompHeaderAccessor stompAccessor = StompHeaderAccessor.wrap(message);
			SimpMessageType messageType = SimpMessageHeaderAccessor.getMessageType(message.getHeaders());
			if (SimpMessageType.CONNECT_ACK.equals(messageType)) {
				stompAccessor = convertConnectAcktoStompConnected(stompAccessor);
			}
			else if (SimpMessageType.DISCONNECT_ACK.equals(messageType)) {
				String receipt = getDisconnectReceipt(stompAccessor);
				if (receipt != null) {
					stompAccessor = StompHeaderAccessor.create(StompCommand.RECEIPT);
					stompAccessor.setReceiptId(receipt);
				}
				else {
					stompAccessor = StompHeaderAccessor.create(StompCommand.ERROR);
					stompAccessor.setMessage("Session closed.");
				}
			}
			else if (SimpMessageType.HEARTBEAT.equals(messageType)) {
				stompAccessor = StompHeaderAccessor.createForHeartbeat();
			}
			else if (stompAccessor.getCommand() == null || StompCommand.SEND.equals(stompAccessor.getCommand())) {
				stompAccessor.updateStompCommandAsServerMessage();
			}
			return stompAccessor;
		}
	}

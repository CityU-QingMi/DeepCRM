	@Override
	protected void flushCache() throws SockJsTransportFailureException {
		String[] messages = new String[getMessageCache().size()];
		for (int i = 0; i < messages.length; i++) {
			messages[i] = getMessageCache().poll();
		}
		SockJsMessageCodec messageCodec = getSockJsServiceConfig().getMessageCodec();
		SockJsFrame frame = SockJsFrame.messageFrame(messageCodec, messages);
		writeFrame(frame);
	}

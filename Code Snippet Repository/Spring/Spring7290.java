	protected ConnectionHandlingStompSession createSession(
			@Nullable StompHeaders connectHeaders, StompSessionHandler handler) {

		connectHeaders = processConnectHeaders(connectHeaders);
		DefaultStompSession session = new DefaultStompSession(handler, connectHeaders);
		session.setMessageConverter(getMessageConverter());
		session.setTaskScheduler(getTaskScheduler());
		session.setReceiptTimeLimit(getReceiptTimeLimit());
		return session;
	}

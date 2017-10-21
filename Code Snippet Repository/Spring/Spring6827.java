	@Override
	public void setupMessageListener(Object messageListener) {
		if (messageListener instanceof MessageListener) {
			setMessageListener((MessageListener) messageListener);
		}
		else {
			throw new IllegalArgumentException("Unsupported message listener '" +
					messageListener.getClass().getName() + "': only '" + MessageListener.class.getName() +
					"' type is supported");
		}
	}

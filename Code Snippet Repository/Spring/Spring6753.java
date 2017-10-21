	@Override
	@Nullable
	public <T> T execute(final @Nullable Destination destination, final ProducerCallback<T> action) throws JmsException {
		Assert.notNull(action, "Callback object must not be null");
		return execute(session -> {
			MessageProducer producer = createProducer(session, destination);
			try {
				return action.doInJms(session, producer);
			}
			finally {
				JmsUtils.closeMessageProducer(producer);
			}
		}, false);
	}

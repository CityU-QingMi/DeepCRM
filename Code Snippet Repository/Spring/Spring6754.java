	@Override
	@Nullable
	public <T> T execute(final String destinationName, final ProducerCallback<T> action) throws JmsException {
		Assert.notNull(action, "Callback object must not be null");
		return execute(session -> {
			Destination destination = resolveDestinationName(session, destinationName);
			MessageProducer producer = createProducer(session, destination);
			try {
				return action.doInJms(session, producer);
			}
			finally {
				JmsUtils.closeMessageProducer(producer);
			}
		}, false);
	}

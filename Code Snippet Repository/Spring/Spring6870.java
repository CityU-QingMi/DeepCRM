	@Nullable
	protected Message receiveFromConsumer(MessageConsumer consumer, long timeout) throws JMSException {
		if (timeout > 0) {
			return consumer.receive(timeout);
		}
		else if (timeout < 0) {
			return consumer.receiveNoWait();
		}
		else {
			return consumer.receive();
		}
	}

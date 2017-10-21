	@SuppressWarnings("")
	public static <T> Message<T> createMessage(@Nullable T payload, MessageHeaders messageHeaders) {
		Assert.notNull(payload, "Payload must not be null");
		Assert.notNull(messageHeaders, "MessageHeaders must not be null");
		if (payload instanceof Throwable) {
			return (Message<T>) new ErrorMessage((Throwable) payload, messageHeaders);
		}
		else {
			return new GenericMessage<>(payload, messageHeaders);
		}
	}

	@SuppressWarnings("")
	public Message<T> build() {
		if (this.originalMessage != null && !this.headerAccessor.isModified()) {
			return this.originalMessage;
		}
		MessageHeaders headersToUse = this.headerAccessor.toMessageHeaders();
		if (this.payload instanceof Throwable) {
			return (Message<T>) new ErrorMessage((Throwable) this.payload, headersToUse);
		}
		else {
			return new GenericMessage<>(this.payload, headersToUse);
		}
	}

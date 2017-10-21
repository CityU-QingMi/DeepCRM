	@Override
	protected boolean canConvertTo(Object payload, @Nullable MessageHeaders headers) {
		if (!supportsMimeType(headers)) {
			return false;
		}
		AtomicReference<Throwable> causeRef = new AtomicReference<>();
		if (this.objectMapper.canSerialize(payload.getClass(), causeRef)) {
			return true;
		}
		logWarningIfNecessary(payload.getClass(), causeRef.get());
		return false;
	}

	@Override
	@SuppressWarnings("")
	public <T> T getNativeResponse(@Nullable Class<T> requiredType) {
		if (requiredType != null) {
			Object response = getExternalContext().getResponse();
			if (requiredType.isInstance(response)) {
				return (T) response;
			}
		}
		return null;
	}

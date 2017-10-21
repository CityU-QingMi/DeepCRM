	@Override
	@SuppressWarnings("")
	public <T> T getNativeRequest(@Nullable Class<T> requiredType) {
		if (requiredType != null) {
			Object request = getExternalContext().getRequest();
			if (requiredType.isInstance(request)) {
				return (T) request;
			}
		}
		return null;
	}

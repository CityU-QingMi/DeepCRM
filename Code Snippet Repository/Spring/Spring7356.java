	@SuppressWarnings("")
	@Nullable
	public static <T extends MessageHeaderAccessor> T getAccessor(
			MessageHeaders messageHeaders, @Nullable Class<T> requiredType) {

		if (messageHeaders instanceof MutableMessageHeaders) {
			MutableMessageHeaders mutableHeaders = (MutableMessageHeaders) messageHeaders;
			MessageHeaderAccessor headerAccessor = mutableHeaders.getAccessor();
			if (requiredType == null || requiredType.isInstance(headerAccessor))  {
				return (T) headerAccessor;
			}
		}
		return null;
	}

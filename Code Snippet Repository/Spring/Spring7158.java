	@Override
	@Nullable
	protected Object resolveArgumentInternal(MethodParameter parameter, Message<?> message, String name)
			throws Exception {

		Object headerValue = message.getHeaders().get(name);
		Object nativeHeaderValue = getNativeHeaderValue(message, name);

		if (headerValue != null && nativeHeaderValue != null) {
			if (logger.isWarnEnabled()) {
				logger.warn("Message headers contain two values for the same header '" + name + "', " +
						"one in the top level header map and a second in the nested map with native headers. " +
						"Using the value from top level map. " +
						"Use 'nativeHeader.myHeader' to resolve to the value from the nested native header map." );
			}
		}

		return (headerValue != null ? headerValue : nativeHeaderValue);
	}

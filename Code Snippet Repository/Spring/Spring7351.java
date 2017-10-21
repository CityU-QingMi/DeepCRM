	protected void verifyType(@Nullable String headerName, @Nullable Object headerValue) {
		if (headerName != null && headerValue != null) {
			if (MessageHeaders.ERROR_CHANNEL.equals(headerName) ||
					MessageHeaders.REPLY_CHANNEL.endsWith(headerName)) {
				if (!(headerValue instanceof MessageChannel || headerValue instanceof String)) {
					throw new IllegalArgumentException(
							"'" + headerName + "' header value must be a MessageChannel or String");
				}
			}
		}
	}

	@Override
	@Nullable
	public MimeType resolve(@Nullable MessageHeaders headers) {
		if (headers == null || headers.get(MessageHeaders.CONTENT_TYPE) == null) {
			return this.defaultMimeType;
		}
		Object value = headers.get(MessageHeaders.CONTENT_TYPE);
		if (value == null) {
			return null;
		}
		else if (value instanceof MimeType) {
			return (MimeType) value;
		}
		else if (value instanceof String) {
			return MimeType.valueOf((String) value);
		}
		else {
			throw new IllegalArgumentException(
					"Unknown type for contentType header value: " + value.getClass());
		}
	}

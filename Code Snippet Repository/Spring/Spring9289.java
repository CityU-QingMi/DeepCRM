	@Nullable
	private MediaType updateContentType(ReactiveHttpOutputMessage message, @Nullable MediaType mediaType) {
		MediaType result = message.getHeaders().getContentType();
		if (result != null) {
			return result;
		}
		MediaType fallback = this.defaultMediaType;
		result = (useFallback(mediaType, fallback) ? fallback : mediaType);
		if (result != null) {
			result = addDefaultCharset(result, fallback);
			message.getHeaders().setContentType(result);
		}
		return result;
	}

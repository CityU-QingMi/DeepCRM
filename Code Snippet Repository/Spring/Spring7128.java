	@Override
	@Nullable
	protected Object convertToInternal(
			Object payload, @Nullable MessageHeaders headers, @Nullable Object conversionHint) {

		if (byte[].class == getSerializedPayloadClass()) {
			Charset charset = getContentTypeCharset(getMimeType(headers));
			payload = ((String) payload).getBytes(charset);
		}
		return payload;
	}

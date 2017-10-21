	private String appendPayload(Object payload) {
		if (payload.getClass() != byte[].class) {
			throw new IllegalStateException(
					"Expected byte array payload but got: " + ClassUtils.getQualifiedName(payload.getClass()));
		}
		byte[] bytes = (byte[]) payload;
		String contentType = (getContentType() != null ? " " + getContentType().toString() : "");
		if (bytes.length == 0 || getContentType() == null || !isReadableContentType()) {
			return contentType;
		}
		Charset charset = getContentType().getCharset();
		charset = (charset != null ? charset : StandardCharsets.UTF_8);
		return (bytes.length < 80) ?
				contentType + " payload=" + new String(bytes, charset) :
				contentType + " payload=" + new String(Arrays.copyOf(bytes, 80), charset) + "...(truncated)";
	}

	private String formatBody(@Nullable MediaType contentType, MonoProcessor<byte[]> body) {
		if (body.isSuccess()) {
			byte[] bytes = body.block(Duration.ZERO);
			if (ObjectUtils.isEmpty(bytes)) {
				return "No content";
			}
			if (contentType == null) {
				return "Unknown content type (" + bytes.length + " bytes)";
			}
			Charset charset = contentType.getCharset();
			if (charset != null) {
				return new String(bytes, charset);
			}
			if (PRINTABLE_MEDIA_TYPES.stream().anyMatch(contentType::isCompatibleWith)) {
				return new String(bytes, StandardCharsets.UTF_8);
			}
			return "Unknown charset (" + bytes.length + " bytes)";
		}
		else if (body.isError()) {
			return "I/O failure: " + body.getError();
		}
		else {
			return "Content not available yet";
		}
	}

	@Nullable
	private byte[] readPayload(ByteBuffer byteBuffer, StompHeaderAccessor headerAccessor) {
		Integer contentLength;
		try {
			contentLength = headerAccessor.getContentLength();
		}
		catch (NumberFormatException ex) {
			if (logger.isWarnEnabled()) {
				logger.warn("Ignoring invalid content-length: '" + headerAccessor);
			}
			contentLength = null;
		}

		if (contentLength != null && contentLength >= 0) {
			if (byteBuffer.remaining() > contentLength) {
				byte[] payload = new byte[contentLength];
				byteBuffer.get(payload);
				if (byteBuffer.get() != 0) {
					throw new StompConversionException("Frame must be terminated with a null octet");
				}
				return payload;
			}
			else {
				return null;
			}
		}
		else {
			ByteArrayOutputStream payload = new ByteArrayOutputStream(256);
			while (byteBuffer.remaining() > 0) {
				byte b = byteBuffer.get();
				if (b == 0) {
					return payload.toByteArray();
				}
				else {
					payload.write(b);
				}
			}
		}
		return null;
	}

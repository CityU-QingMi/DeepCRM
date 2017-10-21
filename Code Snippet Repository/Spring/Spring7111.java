	@Override
	@Nullable
	public final Message<?> toMessage(Object payload, @Nullable MessageHeaders headers, @Nullable Object conversionHint) {
		if (!canConvertTo(payload, headers)) {
			return null;
		}

		Object payloadToUse = convertToInternal(payload, headers, conversionHint);
		if (payloadToUse == null) {
			return null;
		}

		MimeType mimeType = getDefaultContentType(payloadToUse);
		if (headers != null) {
			MessageHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(headers, MessageHeaderAccessor.class);
			if (accessor != null && accessor.isMutable()) {
				if (mimeType != null) {
					accessor.setHeaderIfAbsent(MessageHeaders.CONTENT_TYPE, mimeType);
				}
				return MessageBuilder.createMessage(payloadToUse, accessor.getMessageHeaders());
			}
		}

		MessageBuilder<?> builder = MessageBuilder.withPayload(payloadToUse);
		if (headers != null) {
			builder.copyHeaders(headers);
		}
		if (mimeType != null) {
			builder.setHeaderIfAbsent(MessageHeaders.CONTENT_TYPE, mimeType);
		}
		return builder.build();
	}

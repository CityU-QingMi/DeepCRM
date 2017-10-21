	protected Message<?> doConvert(Object payload, @Nullable Map<String, Object> headers,
			@Nullable MessagePostProcessor postProcessor) {

		MessageHeaders messageHeaders = null;
		Object conversionHint = (headers != null ? headers.get(CONVERSION_HINT_HEADER) : null);

		Map<String, Object> headersToUse = processHeadersToSend(headers);
		if (headersToUse != null) {
			if (headersToUse instanceof MessageHeaders) {
				messageHeaders = (MessageHeaders) headersToUse;
			}
			else {
				messageHeaders = new MessageHeaders(headersToUse);
			}
		}

		MessageConverter converter = getMessageConverter();
		Message<?> message = (converter instanceof SmartMessageConverter ?
				((SmartMessageConverter) converter).toMessage(payload, messageHeaders, conversionHint) :
				converter.toMessage(payload, messageHeaders));
		if (message == null) {
			String payloadType = payload.getClass().getName();
			Object contentType = (messageHeaders != null ? messageHeaders.get(MessageHeaders.CONTENT_TYPE) : null);
			throw new MessageConversionException("Unable to convert payload with type='" + payloadType +
					"', contentType='" + contentType + "', converter=[" + getMessageConverter() + "]");
		}
		if (postProcessor != null) {
			message = postProcessor.postProcessMessage(message);
		}
		return message;
	}

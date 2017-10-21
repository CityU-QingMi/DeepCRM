	private Object convertPayload(Message<?> message, MethodParameter parameter, Class<?> targetPayloadType) {
		Object result = null;
		if (this.converter instanceof SmartMessageConverter) {
			SmartMessageConverter smartConverter = (SmartMessageConverter) this.converter;
			result = smartConverter.fromMessage(message, targetPayloadType, parameter);
		}
		else if (this.converter != null) {
			result = this.converter.fromMessage(message, targetPayloadType);
		}

		if (result == null) {
			throw new MessageConversionException(message, "No converter found from actual payload type '" +
					ClassUtils.getDescriptiveType(message.getPayload()) + "' to expected payload type '" +
					ClassUtils.getQualifiedName(targetPayloadType) + "'");
		}
		return result;
	}

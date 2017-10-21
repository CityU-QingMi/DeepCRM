	@Override
	public Object resolveArgument(MethodParameter parameter, Message<?> message) throws Exception {
		Class<?> targetMessageType = parameter.getParameterType();
		Class<?> targetPayloadType = getPayloadType(parameter);

		if (!targetMessageType.isAssignableFrom(message.getClass())) {
			throw new MethodArgumentTypeMismatchException(message, parameter, "Actual message type '" +
					ClassUtils.getDescriptiveType(message) + "' does not match expected type '" +
					ClassUtils.getQualifiedName(targetMessageType) + "'");
		}

		Object payload = message.getPayload();
		if (targetPayloadType.isInstance(payload)) {
			return message;
		}

		if (isEmptyPayload(payload)) {
			throw new MessageConversionException(message, "Cannot convert from actual payload type '" +
					ClassUtils.getDescriptiveType(payload) + "' to expected payload type '" +
					ClassUtils.getQualifiedName(targetPayloadType) + "' when payload is empty");
		}

		payload = convertPayload(message, parameter, targetPayloadType);
		return MessageBuilder.createMessage(payload, message.getHeaders());
	}

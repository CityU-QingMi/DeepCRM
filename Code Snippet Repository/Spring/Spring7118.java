	@Override
	@Nullable
	public Object fromMessage(Message<?> message, Class<?> targetClass) {
		Object payload = message.getPayload();
		if (this.conversionService.canConvert(payload.getClass(), targetClass)) {
			try {
				return this.conversionService.convert(payload, targetClass);
			}
			catch (ConversionException ex) {
				throw new MessageConversionException(message, "Failed to convert message payload '" +
						payload + "' to '" + targetClass.getName() + "'", ex);
			}
		}
		return (ClassUtils.isAssignableValue(targetClass, payload) ? payload : null);
	}

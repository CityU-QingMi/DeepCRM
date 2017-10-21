	@Override
	@Nullable
	protected Object convertFromInternal(Message<?> message, Class<?> targetClass, @Nullable Object conversionHint) {
		JavaType javaType = this.objectMapper.constructType(targetClass);
		Object payload = message.getPayload();
		Class<?> view = getSerializationView(conversionHint);
		// Note: in the view case, calling withType instead of forType for compatibility with Jackson <2.5
		try {
			if (payload instanceof byte[]) {
				if (view != null) {
					return this.objectMapper.readerWithView(view).forType(javaType).readValue((byte[]) payload);
				}
				else {
					return this.objectMapper.readValue((byte[]) payload, javaType);
				}
			}
			else {
				if (view != null) {
					return this.objectMapper.readerWithView(view).forType(javaType).readValue(payload.toString());
				}
				else {
					return this.objectMapper.readValue(payload.toString(), javaType);
				}
			}
		}
		catch (IOException ex) {
			throw new MessageConversionException(message, "Could not read JSON: " + ex.getMessage(), ex);
		}
	}

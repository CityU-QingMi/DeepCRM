	public Jackson2ObjectMapperBuilder serializers(JsonSerializer<?>... serializers) {
		for (JsonSerializer<?> serializer : serializers) {
			Class<?> handledType = serializer.handledType();
			if (handledType == null || handledType == Object.class) {
				throw new IllegalArgumentException("Unknown handled type in " + serializer.getClass().getName());
			}
			this.serializers.put(serializer.handledType(), serializer);
		}
		return this;
	}

	public Jackson2ObjectMapperBuilder deserializers(JsonDeserializer<?>... deserializers) {
		for (JsonDeserializer<?> deserializer : deserializers) {
			Class<?> handledType = deserializer.handledType();
			if (handledType == null || handledType == Object.class) {
				throw new IllegalArgumentException("Unknown handled type in " + deserializer.getClass().getName());
			}
			this.deserializers.put(deserializer.handledType(), deserializer);
		}
		return this;
	}

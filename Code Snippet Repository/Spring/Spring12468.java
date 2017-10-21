	protected void writeContent(OutputStream stream, Object object) throws IOException {
		JsonGenerator generator = this.objectMapper.getFactory().createGenerator(stream, this.encoding);

		writePrefix(generator, object);
		Class<?> serializationView = null;
		FilterProvider filters = null;
		Object value = object;

		if (value instanceof MappingJacksonValue) {
			MappingJacksonValue container = (MappingJacksonValue) value;
			value = container.getValue();
			serializationView = container.getSerializationView();
			filters = container.getFilters();
		}
		if (serializationView != null) {
			this.objectMapper.writerWithView(serializationView).writeValue(generator, value);
		}
		else if (filters != null) {
			this.objectMapper.writer(filters).writeValue(generator, value);
		}
		else {
			this.objectMapper.writeValue(generator, value);
		}
		writeSuffix(generator, object);
		generator.flush();
	}

	@Override
	@Nullable
	protected Object convertToInternal(Object payload, @Nullable MessageHeaders headers, @Nullable Object conversionHint) {
		try {
			Class<?> view = getSerializationView(conversionHint);
			if (byte[].class == getSerializedPayloadClass()) {
				ByteArrayOutputStream out = new ByteArrayOutputStream(1024);
				JsonEncoding encoding = getJsonEncoding(getMimeType(headers));
				JsonGenerator generator = this.objectMapper.getFactory().createGenerator(out, encoding);
				if (view != null) {
					this.objectMapper.writerWithView(view).writeValue(generator, payload);
				}
				else {
					this.objectMapper.writeValue(generator, payload);
				}
				payload = out.toByteArray();
			}
			else {
				Writer writer = new StringWriter();
				if (view != null) {
					this.objectMapper.writerWithView(view).writeValue(writer, payload);
				}
				else {
					this.objectMapper.writeValue(writer, payload);
				}
				payload = writer.toString();
			}
		}
		catch (IOException ex) {
			throw new MessageConversionException("Could not write JSON: " + ex.getMessage(), ex);
		}
		return payload;
	}

	@Override
	protected Flux<DataBuffer> encode(Object value, DataBufferFactory dataBufferFactory,
			ResolvableType type, @Nullable MimeType mimeType, @Nullable Map<String, Object> hints) {
		try {
			DataBuffer buffer = dataBufferFactory.allocateBuffer(1024);
			OutputStream outputStream = buffer.asOutputStream();
			Class<?> clazz = ClassUtils.getUserClass(value);
			Marshaller marshaller = this.jaxbContexts.createMarshaller(clazz);
			marshaller.setProperty(Marshaller.JAXB_ENCODING, StandardCharsets.UTF_8.name());
			marshaller.marshal(value, outputStream);
			return Flux.just(buffer);
		}
		catch (MarshalException ex) {
			return Flux.error(new EncodingException("Could not marshal " + value.getClass() + " to XML", ex));
		}
		catch (JAXBException ex) {
			return Flux.error(new CodecException("Invalid JAXB configuration", ex));
		}
	}

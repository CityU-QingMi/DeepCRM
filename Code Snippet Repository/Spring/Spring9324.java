	@Override
	public Flux<Object> decode(Publisher<DataBuffer> inputStream, ResolvableType elementType,
			@Nullable MimeType mimeType, @Nullable Map<String, Object> hints) {

		Class<?> outputClass = elementType.getRawClass();
		Assert.state(outputClass != null, "Unresolvable output class");

		Flux<XMLEvent> xmlEventFlux = this.xmlEventDecoder.decode(
				inputStream, ResolvableType.forClass(XMLEvent.class), mimeType, hints);

		QName typeName = toQName(outputClass);
		Flux<List<XMLEvent>> splitEvents = split(xmlEventFlux, typeName);

		return splitEvents.map(events -> unmarshal(events, outputClass));
	}

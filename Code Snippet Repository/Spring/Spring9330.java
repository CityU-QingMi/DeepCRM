	@Override
	@SuppressWarnings({"", ""})
	public Flux<XMLEvent> decode(Publisher<DataBuffer> inputStream, ResolvableType elementType,
			@Nullable MimeType mimeType, @Nullable Map<String, Object> hints) {

		Flux<DataBuffer> flux = Flux.from(inputStream);
		if (useAalto) {
			AaltoDataBufferToXmlEvent aaltoMapper = new AaltoDataBufferToXmlEvent();
			return flux.flatMap(aaltoMapper)
					.doFinally(signalType -> aaltoMapper.endOfInput());
		}
		else {
			Mono<DataBuffer> singleBuffer = flux.reduce(DataBuffer::write);
			return singleBuffer.
					flatMapMany(dataBuffer -> {
						try {
							InputStream is = dataBuffer.asInputStream();
							Iterator eventReader = inputFactory.createXMLEventReader(is);
							return Flux.fromIterable((Iterable<XMLEvent>) () -> eventReader)
									.doFinally(t -> {
										DataBufferUtils.release(dataBuffer);
									});
						}
						catch (XMLStreamException ex) {
							return Mono.error(ex);
						}
					});
		}
	}

	@Override
	public Flux<Object> read(ResolvableType elementType, ReactiveHttpInputMessage message,
			Map<String, Object> hints) {

		boolean shouldWrap = isServerSentEvent(elementType);
		ResolvableType valueType = (shouldWrap ? elementType.getGeneric(0) : elementType);

		return Flux.from(message.getBody())
				.concatMap(ServerSentEventHttpMessageReader::splitOnNewline)
				.map(buffer -> {
					CharBuffer charBuffer = StandardCharsets.UTF_8.decode(buffer.asByteBuffer());
					DataBufferUtils.release(buffer);
					return charBuffer.toString();
				})
				.bufferUntil(line -> line.equals("\n"))
				.concatMap(rawLines -> {
					String[] lines = rawLines.stream().collect(joining()).split("\\r?\\n");
					ServerSentEvent<Object> event = buildEvent(lines, valueType, hints);
					return (shouldWrap ? Mono.just(event) : Mono.justOrEmpty(event.data()));
				})
				.cast(Object.class);
	}

	@SuppressWarnings("")
	private <T> Flux<DataBuffer> encodeData(@Nullable T data, ResolvableType valueType,
			DataBufferFactory factory, Map<String, Object> hints) {

		if (data == null) {
			return Flux.empty();
		}

		if (data instanceof String) {
			String text = (String) data;
			return Flux.from(encodeText(text.replaceAll("\\n", "\ndata:") + "\n", factory));
		}

		if (this.encoder == null) {
			return Flux.error(new CodecException("No SSE encoder configured and the data is not String."));
		}

		return ((Encoder<T>) this.encoder)
				.encode(Mono.just(data), factory, valueType, MediaType.TEXT_EVENT_STREAM, hints)
				.concatWith(encodeText("\n", factory));
	}

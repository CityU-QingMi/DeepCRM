	@Nullable
	private Object decodeData(String data, ResolvableType dataType, Map<String, Object> hints) {
		if (String.class == dataType.resolve()) {
			return data.substring(0, data.length() - 1);
		}

		if (this.decoder == null) {
			return Flux.error(new CodecException("No SSE decoder configured and the data is not String."));
		}

		byte[] bytes = data.getBytes(StandardCharsets.UTF_8);
		Mono<DataBuffer> input = Mono.just(bufferFactory.wrap(bytes));

		return this.decoder
				.decodeToMono(input, dataType, MediaType.TEXT_EVENT_STREAM, hints)
				.block(Duration.ZERO);
	}

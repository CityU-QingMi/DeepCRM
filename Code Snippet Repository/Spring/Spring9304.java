	private Flux<Publisher<DataBuffer>> encode(Publisher<?> input, DataBufferFactory factory,
			ResolvableType elementType, Map<String, Object> hints) {

		Class<?> elementClass = elementType.getRawClass();
		ResolvableType valueType = (elementClass != null && ServerSentEvent.class.isAssignableFrom(elementClass) ?
				elementType.getGeneric() : elementType);

		return Flux.from(input).map(element -> {

			ServerSentEvent<?> sse = (element instanceof ServerSentEvent ?
					(ServerSentEvent<?>) element : ServerSentEvent.builder().data(element).build());

			StringBuilder sb = new StringBuilder();
			String id = sse.id();
			String event = sse.event();
			Duration retry = sse.retry();
			String comment = sse.comment();
			Object data = sse.data();
			if (id != null) {
				writeField("id", id, sb);
			}
			if (event != null) {
				writeField("event", event, sb);
			}
			if (retry != null) {
				writeField("retry", retry.toMillis(), sb);
			}
			if (comment != null) {
				sb.append(':').append(comment.replaceAll("\\n", "\n:")).append("\n");
			}
			if (data != null) {
				sb.append("data:");
			}

			return Flux.concat(encodeText(sb, factory),
					encodeData(data, valueType, factory, hints),
					encodeText("\n", factory));
		});
	}

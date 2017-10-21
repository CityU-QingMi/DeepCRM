	public static <T, S extends Publisher<ServerSentEvent<T>>> BodyInserter<S, ServerHttpResponse> fromServerSentEvents(
			S eventsPublisher) {

		Assert.notNull(eventsPublisher, "'eventsPublisher' must not be null");
		return (serverResponse, context) -> {
			HttpMessageWriter<ServerSentEvent<T>> messageWriter =
					findMessageWriter(context, SERVER_SIDE_EVENT_TYPE, MediaType.TEXT_EVENT_STREAM);
			return context.serverRequest()
					.map(serverRequest -> messageWriter.write(eventsPublisher, SERVER_SIDE_EVENT_TYPE,
							SERVER_SIDE_EVENT_TYPE, MediaType.TEXT_EVENT_STREAM, serverRequest,
							serverResponse, context.hints()))
					.orElseGet(() -> messageWriter.write(eventsPublisher, SERVER_SIDE_EVENT_TYPE,
							MediaType.TEXT_EVENT_STREAM, serverResponse, context.hints()));
		};
	}

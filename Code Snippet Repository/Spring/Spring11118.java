	private static <T> HttpMessageWriter<T> findMessageWriter(
			BodyInserter.Context context, ResolvableType type, MediaType mediaType) {

		return context.messageWriters().stream()
				.filter(messageWriter -> messageWriter.canWrite(type, mediaType))
				.findFirst()
				.map(BodyInserters::<T>cast)
				.orElseThrow(() -> new IllegalStateException(
						"Could not find HttpMessageWriter that supports " + mediaType));
	}

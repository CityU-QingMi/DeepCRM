	private static <T> HttpMessageReader<T> messageReader(ResolvableType elementType,
			MediaType mediaType, BodyExtractor.Context context) {
		return context.messageReaders().stream()
				.filter(messageReader -> messageReader.canRead(elementType, mediaType))
				.findFirst()
				.map(BodyExtractors::<T>cast)
				.orElseThrow(() -> new IllegalStateException(
						"Could not find HttpMessageReader that supports \"" + mediaType +
								"\" and \"" + elementType + "\""));
	}

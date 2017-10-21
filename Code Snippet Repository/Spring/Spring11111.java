	private static <T, S extends Publisher<T>> S readWithMessageReaders(
			ReactiveHttpInputMessage inputMessage, BodyExtractor.Context context, ResolvableType elementType,
			Function<HttpMessageReader<T>, S> readerFunction,
			Function<UnsupportedMediaTypeException, S> unsupportedError,
			Supplier<S> empty) {

		if (VOID_TYPE.equals(elementType)) {
			return empty.get();
		}
		MediaType contentType = contentType(inputMessage);
		List<HttpMessageReader<?>> messageReaders = context.messageReaders();
		return messageReaders.stream()
				.filter(r -> r.canRead(elementType, contentType))
				.findFirst()
				.map(BodyExtractors::<T>cast)
				.map(readerFunction)
				.orElseGet(() -> {
					List<MediaType> supportedMediaTypes = messageReaders.stream()
							.flatMap(reader -> reader.getReadableMediaTypes().stream())
							.collect(Collectors.toList());
					UnsupportedMediaTypeException error =
							new UnsupportedMediaTypeException(contentType, supportedMediaTypes);
					return unsupportedError.apply(error);
				});
	}

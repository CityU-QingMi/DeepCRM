	@SuppressWarnings("")
	private AbstractMessageReaderArgumentResolver resolver(Decoder<?>... decoders) {
		List<HttpMessageReader<?>> readers = new ArrayList<>();
		Arrays.asList(decoders).forEach(decoder -> readers.add(new DecoderHttpMessageReader<>(decoder)));
		return new AbstractMessageReaderArgumentResolver(readers) {
			@Override
			public boolean supportsParameter(MethodParameter parameter) {
				return false;
			}
			@Override
			public Mono<Object> resolveArgument(MethodParameter parameter, BindingContext bindingContext, ServerWebExchange exchange) {
				return null;
			}
		};
	}

	private Flux<TokenBuffer> tokenize(Publisher<DataBuffer> input, boolean tokenizeArrayElements) {
		try {
			JsonFactory factory = getObjectMapper().getFactory();
			JsonParser parser = factory.createNonBlockingByteArrayParser();
			Jackson2Tokenizer tokenizer = new Jackson2Tokenizer(parser, tokenizeArrayElements);
			return Flux.from(input).flatMap(tokenizer).doFinally(t -> tokenizer.endOfInput());
		}
		catch (IOException ex) {
			return Flux.error(new UncheckedIOException(ex));
		}
	}

	@Test
	public void jackson2EncoderOverride() throws Exception {

		Jackson2JsonDecoder decoder = new Jackson2JsonDecoder();
		this.configurer.defaultCodecs().jackson2JsonDecoder(decoder);

		assertSame(decoder, this.configurer.getReaders().stream()
				.filter(reader -> ServerSentEventHttpMessageReader.class.equals(reader.getClass()))
				.map(reader -> (ServerSentEventHttpMessageReader) reader)
				.findFirst()
				.map(ServerSentEventHttpMessageReader::getDecoder)
				.filter(e -> e == decoder).orElse(null));
	}

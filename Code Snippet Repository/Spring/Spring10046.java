	@Test
	public void jackson2DecoderOverride() throws Exception {

		Jackson2JsonDecoder decoder = new Jackson2JsonDecoder();
		this.configurer.defaultCodecs().jackson2JsonDecoder(decoder);

		assertSame(decoder, this.configurer.getReaders().stream()
				.filter(writer -> writer instanceof DecoderHttpMessageReader)
				.map(writer -> ((DecoderHttpMessageReader<?>) writer).getDecoder())
				.filter(e -> Jackson2JsonDecoder.class.equals(e.getClass()))
				.findFirst()
				.filter(e -> e == decoder).orElse(null));
	}

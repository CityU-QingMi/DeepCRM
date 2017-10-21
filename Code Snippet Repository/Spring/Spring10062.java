	@Test
	public void jackson2EncoderOverride() throws Exception {

		Jackson2JsonEncoder encoder = new Jackson2JsonEncoder();
		this.configurer.defaultCodecs().jackson2JsonEncoder(encoder);

		assertSame(encoder, this.configurer.getWriters().stream()
				.filter(writer -> ServerSentEventHttpMessageWriter.class.equals(writer.getClass()))
				.map(writer -> (ServerSentEventHttpMessageWriter) writer)
				.findFirst()
				.map(ServerSentEventHttpMessageWriter::getEncoder)
				.filter(e -> e == encoder).orElse(null));
	}

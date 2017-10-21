	@Test
	public void jackson2EncoderOverride() throws Exception {

		Jackson2JsonEncoder encoder = new Jackson2JsonEncoder();
		this.configurer.defaultCodecs().jackson2JsonEncoder(encoder);

		assertSame(encoder, this.configurer.getWriters().stream()
				.filter(writer -> writer instanceof EncoderHttpMessageWriter)
				.map(writer -> ((EncoderHttpMessageWriter<?>) writer).getEncoder())
				.filter(e -> Jackson2JsonEncoder.class.equals(e.getClass()))
				.findFirst()
				.filter(e -> e == encoder).orElse(null));
	}

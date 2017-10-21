	@Test
	public void defaultsOffCustomReaders() throws Exception {

		Decoder<?> customDecoder1 = mock(Decoder.class);
		Decoder<?> customDecoder2 = mock(Decoder.class);

		when(customDecoder1.canDecode(ResolvableType.forClass(Object.class), null)).thenReturn(false);
		when(customDecoder2.canDecode(ResolvableType.forClass(Object.class), null)).thenReturn(true);

		HttpMessageReader<?> customReader1 = mock(HttpMessageReader.class);
		HttpMessageReader<?> customReader2 = mock(HttpMessageReader.class);

		when(customReader1.canRead(ResolvableType.forClass(Object.class), null)).thenReturn(false);
		when(customReader2.canRead(ResolvableType.forClass(Object.class), null)).thenReturn(true);

		this.configurer.customCodecs().decoder(customDecoder1);
		this.configurer.customCodecs().decoder(customDecoder2);

		this.configurer.customCodecs().reader(customReader1);
		this.configurer.customCodecs().reader(customReader2);

		this.configurer.registerDefaults(false);

		List<HttpMessageReader<?>> readers = this.configurer.getReaders();

		assertEquals(4, readers.size());
		assertSame(customDecoder1, getNextDecoder(readers));
		assertSame(customReader1, readers.get(this.index.getAndIncrement()));
		assertSame(customDecoder2, getNextDecoder(readers));
		assertSame(customReader2, readers.get(this.index.getAndIncrement()));
	}

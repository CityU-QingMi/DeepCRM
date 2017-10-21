	@Test
	public void defaultAndCustomReaders() throws Exception {

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

		List<HttpMessageReader<?>> readers = this.configurer.getReaders();

		assertEquals(13, readers.size());
		assertEquals(ByteArrayDecoder.class, getNextDecoder(readers).getClass());
		assertEquals(ByteBufferDecoder.class, getNextDecoder(readers).getClass());
		assertEquals(DataBufferDecoder.class, getNextDecoder(readers).getClass());
		assertEquals(ResourceDecoder.class, getNextDecoder(readers).getClass());
		assertEquals(StringDecoder.class, getNextDecoder(readers).getClass());
		assertSame(customDecoder1, getNextDecoder(readers));
		assertSame(customReader1, readers.get(this.index.getAndIncrement()));
		assertEquals(Jaxb2XmlDecoder.class, getNextDecoder(readers).getClass());
		assertEquals(Jackson2JsonDecoder.class, getNextDecoder(readers).getClass());
		assertEquals(Jackson2SmileDecoder.class, getNextDecoder(readers).getClass());
		assertSame(customDecoder2, getNextDecoder(readers));
		assertSame(customReader2, readers.get(this.index.getAndIncrement()));
		assertEquals(StringDecoder.class, getNextDecoder(readers).getClass());
	}

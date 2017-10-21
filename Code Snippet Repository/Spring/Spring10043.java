	@Test
	public void defaultAndCustomWriters() throws Exception {

		Encoder<?> customEncoder1 = mock(Encoder.class);
		Encoder<?> customEncoder2 = mock(Encoder.class);

		when(customEncoder1.canEncode(ResolvableType.forClass(Object.class), null)).thenReturn(false);
		when(customEncoder2.canEncode(ResolvableType.forClass(Object.class), null)).thenReturn(true);

		HttpMessageWriter<?> customWriter1 = mock(HttpMessageWriter.class);
		HttpMessageWriter<?> customWriter2 = mock(HttpMessageWriter.class);

		when(customWriter1.canWrite(ResolvableType.forClass(Object.class), null)).thenReturn(false);
		when(customWriter2.canWrite(ResolvableType.forClass(Object.class), null)).thenReturn(true);

		this.configurer.customCodecs().encoder(customEncoder1);
		this.configurer.customCodecs().encoder(customEncoder2);

		this.configurer.customCodecs().writer(customWriter1);
		this.configurer.customCodecs().writer(customWriter2);

		List<HttpMessageWriter<?>> writers = this.configurer.getWriters();

		assertEquals(13, writers.size());
		assertEquals(ByteArrayEncoder.class, getNextEncoder(writers).getClass());
		assertEquals(ByteBufferEncoder.class, getNextEncoder(writers).getClass());
		assertEquals(DataBufferEncoder.class, getNextEncoder(writers).getClass());
		assertEquals(ResourceHttpMessageWriter.class, writers.get(index.getAndIncrement()).getClass());
		assertEquals(CharSequenceEncoder.class, getNextEncoder(writers).getClass());
		assertSame(customEncoder1, getNextEncoder(writers));
		assertSame(customWriter1, writers.get(this.index.getAndIncrement()));
		assertEquals(Jaxb2XmlEncoder.class, getNextEncoder(writers).getClass());
		assertEquals(Jackson2JsonEncoder.class, getNextEncoder(writers).getClass());
		assertEquals(Jackson2SmileEncoder.class, getNextEncoder(writers).getClass());
		assertSame(customEncoder2, getNextEncoder(writers));
		assertSame(customWriter2, writers.get(this.index.getAndIncrement()));
		assertEquals(CharSequenceEncoder.class, getNextEncoder(writers).getClass());
	}

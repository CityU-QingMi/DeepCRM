	@Test
	public void defaultsOffWithCustomWriters() throws Exception {

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

		this.configurer.registerDefaults(false);

		List<HttpMessageWriter<?>> writers = this.configurer.getWriters();

		assertEquals(4, writers.size());
		assertSame(customEncoder1, getNextEncoder(writers));
		assertSame(customWriter1, writers.get(this.index.getAndIncrement()));
		assertSame(customEncoder2, getNextEncoder(writers));
		assertSame(customWriter2, writers.get(this.index.getAndIncrement()));
	}

	@Test
	public void defaultWriters() throws Exception {
		List<HttpMessageWriter<?>> writers = this.configurer.getWriters();
		assertEquals(10, writers.size());
		assertEquals(ByteArrayEncoder.class, getNextEncoder(writers).getClass());
		assertEquals(ByteBufferEncoder.class, getNextEncoder(writers).getClass());
		assertEquals(DataBufferEncoder.class, getNextEncoder(writers).getClass());
		assertEquals(ResourceHttpMessageWriter.class, writers.get(index.getAndIncrement()).getClass());
		assertStringEncoder(getNextEncoder(writers), true);
		assertEquals(Jaxb2XmlEncoder.class, getNextEncoder(writers).getClass());
		assertEquals(Jackson2JsonEncoder.class, getNextEncoder(writers).getClass());
		assertEquals(Jackson2SmileEncoder.class, getNextEncoder(writers).getClass());
		assertSseWriter(writers);
		assertStringEncoder(getNextEncoder(writers), false);
	}

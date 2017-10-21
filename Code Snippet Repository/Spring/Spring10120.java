	@Test
	public void canWrite() {
		assertTrue(this.converter.canWrite(MultiValueMap.class,
				new MediaType("application", "x-www-form-urlencoded")));
		assertTrue(this.converter.canWrite(MultiValueMap.class,
				new MediaType("multipart", "form-data")));
		assertTrue(this.converter.canWrite(MultiValueMap.class,
				new MediaType("multipart", "form-data", StandardCharsets.UTF_8)));
		assertTrue(this.converter.canWrite(MultiValueMap.class, MediaType.ALL));
	}

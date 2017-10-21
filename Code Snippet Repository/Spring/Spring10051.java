	@Test
	public void canRead() {
		assertTrue(this.reader.canRead(
				ResolvableType.forClassWithGenerics(MultiValueMap.class, String.class, String.class),
				MediaType.APPLICATION_FORM_URLENCODED));

		assertFalse(this.reader.canRead(
				ResolvableType.forClassWithGenerics(MultiValueMap.class, String.class, Object.class),
				MediaType.APPLICATION_FORM_URLENCODED));

		assertFalse(this.reader.canRead(
				ResolvableType.forClassWithGenerics(MultiValueMap.class, Object.class, String.class),
				MediaType.APPLICATION_FORM_URLENCODED));

		assertFalse(this.reader.canRead(
				ResolvableType.forClassWithGenerics(Map.class, String.class, String.class),
				MediaType.APPLICATION_FORM_URLENCODED));

		assertFalse(this.reader.canRead(
				ResolvableType.forClassWithGenerics(MultiValueMap.class, String.class, String.class),
				MediaType.MULTIPART_FORM_DATA));
	}

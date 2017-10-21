	@Test
	public void canDecode() {
		assertTrue(this.decoder.canDecode(ResolvableType.forClass(Pojo.class),
				MediaType.APPLICATION_XML));
		assertTrue(this.decoder.canDecode(ResolvableType.forClass(Pojo.class),
				MediaType.TEXT_XML));
		assertFalse(this.decoder.canDecode(ResolvableType.forClass(Pojo.class),
				MediaType.APPLICATION_JSON));
		assertTrue(this.decoder.canDecode(ResolvableType.forClass(TypePojo.class),
				MediaType.APPLICATION_XML));
		assertFalse(this.decoder.canDecode(ResolvableType.forClass(getClass()),
				MediaType.APPLICATION_XML));
	}

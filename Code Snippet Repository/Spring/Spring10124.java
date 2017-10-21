	@Test
	public void canRead() {
		assertFalse(this.converter.canRead(Math.class, null));
		assertFalse(this.converter.canRead(Resource.class, null));

		assertTrue(this.converter.canRead(Locale.class, null));
		assertTrue(this.converter.canRead(BigInteger.class, null));

		assertFalse(this.converter.canRead(BigInteger.class, MediaType.TEXT_HTML));
		assertFalse(this.converter.canRead(BigInteger.class, MediaType.TEXT_XML));
		assertFalse(this.converter.canRead(BigInteger.class, MediaType.APPLICATION_XML));
	}

	@Test
	public void canWrite() {
		assertFalse(this.converter.canWrite(Math.class, null));
		assertFalse(this.converter.canWrite(Resource.class, null));

		assertTrue(this.converter.canWrite(Locale.class, null));
		assertTrue(this.converter.canWrite(Double.class, null));

		assertFalse(this.converter.canWrite(BigInteger.class, MediaType.TEXT_HTML));
		assertFalse(this.converter.canWrite(BigInteger.class, MediaType.TEXT_XML));
		assertFalse(this.converter.canWrite(BigInteger.class, MediaType.APPLICATION_XML));

		assertTrue(this.converter.canWrite(BigInteger.class, MediaType.valueOf("text/*")));
	}

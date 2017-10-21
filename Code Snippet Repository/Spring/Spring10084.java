	@Test
	public void canEncode() {
		ResolvableType pojoType = ResolvableType.forClass(Pojo.class);
		assertTrue(this.encoder.canEncode(pojoType, APPLICATION_JSON));
		assertTrue(this.encoder.canEncode(pojoType, null));

		// SPR-15464
		assertTrue(this.encoder.canEncode(ResolvableType.NONE, null));

		// SPR-15910
		assertFalse(this.encoder.canEncode(ResolvableType.forClass(Object.class), APPLICATION_OCTET_STREAM));
	}

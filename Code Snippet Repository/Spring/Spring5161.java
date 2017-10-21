	@Test
	public void canEncode() {
		ResolvableType resourceRegion = ResolvableType.forClass(ResourceRegion.class);
		MimeType allMimeType = MimeType.valueOf("*/*");

		assertFalse(this.encoder.canEncode(ResolvableType.forClass(Resource.class),
				MimeTypeUtils.APPLICATION_OCTET_STREAM));
		assertFalse(this.encoder.canEncode(ResolvableType.forClass(Resource.class), allMimeType));
		assertTrue(this.encoder.canEncode(resourceRegion, MimeTypeUtils.APPLICATION_OCTET_STREAM));
		assertTrue(this.encoder.canEncode(resourceRegion, allMimeType));

		// SPR-15464
		assertFalse(this.encoder.canEncode(ResolvableType.NONE, null));
	}

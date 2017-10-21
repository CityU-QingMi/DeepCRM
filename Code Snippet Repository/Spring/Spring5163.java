	@Test
	public void canDecode() {
		assertTrue(this.decoder.canDecode(ResolvableType.forClass(String.class),
				MimeTypeUtils.TEXT_PLAIN));
		assertTrue(this.decoder.canDecode(ResolvableType.forClass(String.class),
				MimeTypeUtils.TEXT_HTML));
		assertTrue(this.decoder.canDecode(ResolvableType.forClass(String.class),
				MimeTypeUtils.APPLICATION_JSON));
		assertFalse(this.decoder.canDecode(ResolvableType.forClass(Integer.class),
				MimeTypeUtils.TEXT_PLAIN));
		assertFalse(this.decoder.canDecode(ResolvableType.forClass(Object.class),
				MimeTypeUtils.APPLICATION_JSON));
	}

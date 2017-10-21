	@Test
	public void canRead() {
		assertTrue(this.converter.canRead(Msg.class, null));
		assertTrue(this.converter.canRead(Msg.class, ProtobufHttpMessageConverter.PROTOBUF));
		assertTrue(this.converter.canRead(Msg.class, MediaType.APPLICATION_JSON));
		assertTrue(this.converter.canRead(Msg.class, MediaType.APPLICATION_XML));
		assertTrue(this.converter.canRead(Msg.class, MediaType.TEXT_PLAIN));

		// only supported as an output format
		assertFalse(this.converter.canRead(Msg.class, MediaType.TEXT_HTML));
	}

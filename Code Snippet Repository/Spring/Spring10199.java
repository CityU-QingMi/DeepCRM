	@Test
	public void write() throws IOException {
		MockHttpOutputMessage outputMessage = new MockHttpOutputMessage();
		MediaType contentType = ProtobufHttpMessageConverter.PROTOBUF;
		this.converter.write(this.testMsg, contentType, outputMessage);
		assertEquals(contentType, outputMessage.getHeaders().getContentType());
		assertTrue(outputMessage.getBodyAsBytes().length > 0);
		Message result = Msg.parseFrom(outputMessage.getBodyAsBytes());
		assertEquals(this.testMsg, result);

		String messageHeader =
				outputMessage.getHeaders().getFirst(ProtobufHttpMessageConverter.X_PROTOBUF_MESSAGE_HEADER);
		assertEquals("Msg", messageHeader);
		String schemaHeader =
				outputMessage.getHeaders().getFirst(ProtobufHttpMessageConverter.X_PROTOBUF_SCHEMA_HEADER);
		assertEquals("sample.proto", schemaHeader);
	}

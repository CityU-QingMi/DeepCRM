	@Test
	public void prettyPrintWithSse() throws Exception {
		MockHttpOutputMessage outputMessage = new MockHttpOutputMessage();
		outputMessage.getHeaders().setContentType(MediaType.TEXT_EVENT_STREAM);
		PrettyPrintBean bean = new PrettyPrintBean();
		bean.setName("Jason");

		this.converter.setPrettyPrint(true);
		this.converter.writeInternal(bean, null, outputMessage);
		String result = outputMessage.getBodyAsString(StandardCharsets.UTF_8);

		assertEquals("{\ndata:  \"name\" : \"Jason\"\ndata:}", result);
	}

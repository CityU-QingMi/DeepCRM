	@Test
	public void writeSubType() throws Exception {
		MockHttpOutputMessage outputMessage = new MockHttpOutputMessage();
		MyBean bean = new MyBean();
		bean.setString("Foo");
		bean.setNumber(42);

		this.converter.writeInternal(bean, MyInterface.class, outputMessage);

		String result = outputMessage.getBodyAsString(StandardCharsets.UTF_8);
		assertTrue(result.contains("\"string\":\"Foo\""));
		assertTrue(result.contains("\"number\":42"));
	}

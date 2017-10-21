	@Test
	public void jsonp() throws Exception {
		MappingJacksonValue jacksonValue = new MappingJacksonValue("foo");
		jacksonValue.setSerializationView(MyJacksonView1.class);
		jacksonValue.setJsonpFunction("callback");

		MockHttpOutputMessage outputMessage = new MockHttpOutputMessage();
		this.converter.writeInternal(jacksonValue, null, outputMessage);

		assertEquals("/**/callback(\"foo\");", outputMessage.getBodyAsString(StandardCharsets.UTF_8));
	}

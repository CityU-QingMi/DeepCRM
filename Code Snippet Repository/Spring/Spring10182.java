	@Test
	public void jsonpAndJsonView() throws Exception {
		MockHttpOutputMessage outputMessage = new MockHttpOutputMessage();
		JacksonViewBean bean = new JacksonViewBean();
		bean.setWithView1("with");
		bean.setWithView2("with");
		bean.setWithoutView("without");

		MappingJacksonValue jacksonValue = new MappingJacksonValue(bean);
		jacksonValue.setSerializationView(MyJacksonView1.class);
		jacksonValue.setJsonpFunction("callback");
		this.converter.writeInternal(jacksonValue, null, outputMessage);

		String result = outputMessage.getBodyAsString(StandardCharsets.UTF_8);
		assertThat(result, startsWith("/**/callback("));
		assertThat(result, endsWith(");"));
		assertThat(result, containsString("\"withView1\":\"with\""));
		assertThat(result, not(containsString("\"withView2\":\"with\"")));
		assertThat(result, not(containsString("\"withoutView\":\"without\"")));
	}

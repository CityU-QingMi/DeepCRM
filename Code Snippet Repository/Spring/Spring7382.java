	@Test
	public void toMessageJsonView() throws Exception {
		MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();

		Map<String, Object> map = new HashMap<>();
		Method method = getClass().getDeclaredMethod("jsonViewResponse");
		MethodParameter returnType = new MethodParameter(method, -1);
		Message<?> message = converter.toMessage(jsonViewResponse(), new MessageHeaders(map), returnType);
		String actual = new String((byte[]) message.getPayload(), StandardCharsets.UTF_8);

		assertThat(actual, containsString("\"withView1\":\"with\""));
		assertThat(actual, containsString("\"withView2\":\"with\""));
		assertThat(actual, not(containsString("\"withoutView\":\"with\"")));

		method = getClass().getDeclaredMethod("jsonViewPayload", JacksonViewBean.class);
		MethodParameter param = new MethodParameter(method, 0);
		JacksonViewBean back = (JacksonViewBean) converter.fromMessage(message, JacksonViewBean.class, param);
		assertNull(back.getWithView1());
		assertEquals("with", back.getWithView2());
		assertNull(back.getWithoutView());
	}

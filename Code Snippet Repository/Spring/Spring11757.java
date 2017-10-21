	@Test
	public void jacksonTypeWithSubTypeOfListElement() throws Exception {

		MethodParameter returnType = on(TestController.class).resolveReturnType(List.class, Identifiable.class);

		List<SimpleBean> body = Arrays.asList(new SimpleBean(123L, "foo"), new SimpleBean(456L, "bar"));
		this.resultHandler.writeBody(body, returnType, this.exchange).block(Duration.ofSeconds(5));

		assertEquals(APPLICATION_JSON_UTF8, this.exchange.getResponse().getHeaders().getContentType());
		assertResponseBody("[{\"id\":123,\"name\":\"foo\"},{\"id\":456,\"name\":\"bar\"}]");
	}

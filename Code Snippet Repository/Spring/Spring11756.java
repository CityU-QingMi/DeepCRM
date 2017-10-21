	@Test
	public void jacksonTypeOfListElement() throws Exception {

		MethodParameter returnType = on(TestController.class).resolveReturnType(List.class, ParentClass.class);
		List<ParentClass> body = Arrays.asList(new Foo("foo"), new Bar("bar"));
		this.resultHandler.writeBody(body, returnType, this.exchange).block(Duration.ofSeconds(5));

		assertEquals(APPLICATION_JSON_UTF8, this.exchange.getResponse().getHeaders().getContentType());
		assertResponseBody("[{\"type\":\"foo\",\"parentProperty\":\"foo\"}," +
				"{\"type\":\"bar\",\"parentProperty\":\"bar\"}]");
	}

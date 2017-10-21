	@Test
	public void contentNegotiation() throws Exception {
		TestBean value = new TestBean("Joe");
		MethodParameter returnType = on(Handler.class).resolveReturnType(TestBean.class);
		HandlerResult handlerResult = new HandlerResult(new Object(), value, returnType, this.bindingContext);

		MockServerWebExchange exchange = MockServerWebExchange.from(get("/account").accept(APPLICATION_JSON).build());

		TestView defaultView = new TestView("jsonView", APPLICATION_JSON);

		resultHandler(Collections.singletonList(defaultView), new TestViewResolver("account"))
				.handleResult(exchange, handlerResult)
				.block(Duration.ofSeconds(5));

		assertEquals(APPLICATION_JSON, exchange.getResponse().getHeaders().getContentType());
		assertResponseBody(exchange, "jsonView: {" +
				"org.springframework.validation.BindingResult.testBean=" +
				"org.springframework.validation.BeanPropertyBindingResult: 0 errors, " +
				"testBean=TestBean[name=Joe]" +
				"}");
	}

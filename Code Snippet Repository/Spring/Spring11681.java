	@Test
	public void getHandlerEmptyPathMatch() throws Exception {
		Method expected = on(TestController.class).annot(requestMapping("")).resolveMethod();
		ServerWebExchange exchange = MockServerWebExchange.from(get("").build());
		HandlerMethod hm = (HandlerMethod) this.handlerMapping.getHandler(exchange).block();
		assertEquals(expected, hm.getMethod());

		exchange = MockServerWebExchange.from(get("/").build());
		hm = (HandlerMethod) this.handlerMapping.getHandler(exchange).block();
		assertEquals(expected, hm.getMethod());
	}

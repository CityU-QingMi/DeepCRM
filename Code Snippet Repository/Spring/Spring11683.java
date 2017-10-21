	private void testHttpOptions(String requestURI, Set<HttpMethod> allowedMethods) throws Exception {
		ServerWebExchange exchange = MockServerWebExchange.from(MockServerHttpRequest.options(requestURI).build());
		HandlerMethod handlerMethod = (HandlerMethod) this.handlerMapping.getHandler(exchange).block();

		BindingContext bindingContext = new BindingContext();
		InvocableHandlerMethod invocable = new InvocableHandlerMethod(handlerMethod);
		Mono<HandlerResult> mono = invocable.invoke(exchange, bindingContext);

		HandlerResult result = mono.block();
		assertNotNull(result);

		Object value = result.getReturnValue();
		assertNotNull(value);
		assertEquals(HttpHeaders.class, value.getClass());
		assertEquals(allowedMethods, ((HttpHeaders) value).getAllow());
	}

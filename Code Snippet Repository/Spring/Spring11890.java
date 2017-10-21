	@Test
	public void contentNegotiationWithRedirect() throws Exception {

		HandlerResult handlerResult = new HandlerResult(new Object(), "redirect:/",
				on(Handler.class).annotNotPresent(ModelAttribute.class).resolveReturnType(String.class),
				this.bindingContext);

		UrlBasedViewResolver viewResolver = new UrlBasedViewResolver();
		viewResolver.setApplicationContext(new StaticApplicationContext());
		ViewResolutionResultHandler resultHandler = resultHandler(viewResolver);

		MockServerWebExchange exchange = MockServerWebExchange.from(get("/account").accept(APPLICATION_JSON).build());
		resultHandler.handleResult(exchange, handlerResult).block(Duration.ZERO);

		MockServerHttpResponse response = exchange.getResponse();
		assertEquals(303, response.getStatusCode().value());
		assertEquals("/", response.getHeaders().getLocation().toString());
	}

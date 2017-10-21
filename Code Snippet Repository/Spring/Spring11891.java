	private ServerWebExchange testHandle(String path, MethodParameter returnType, Object returnValue,
			String responseBody, ViewResolver... resolvers) throws URISyntaxException {

		Model model = this.bindingContext.getModel();
		model.asMap().clear();
		model.addAttribute("id", "123");
		HandlerResult result = new HandlerResult(new Object(), returnValue, returnType, this.bindingContext);
		MockServerWebExchange exchange = MockServerWebExchange.from(get(path).build());
		resultHandler(resolvers).handleResult(exchange, result).block(Duration.ofSeconds(5));
		assertResponseBody(exchange, responseBody);
		return exchange;
	}

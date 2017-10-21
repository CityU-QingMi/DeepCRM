	@Override
	public void handleRequest(HttpServerExchange exchange) throws Exception {

		ServerHttpRequest request = new UndertowServerHttpRequest(exchange, getDataBufferFactory());
		ServerHttpResponse response = new UndertowServerHttpResponse(exchange, getDataBufferFactory());

		if (HttpMethod.HEAD.equals(request.getMethod())) {
			response = new HttpHeadResponseDecorator(response);
		}

		HandlerResultSubscriber resultSubscriber = new HandlerResultSubscriber(exchange);
		this.httpHandler.handle(request, response).subscribe(resultSubscriber);
	}

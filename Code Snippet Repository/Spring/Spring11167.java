	@Override
	protected Mono<?> getHandlerInternal(ServerWebExchange exchange) {
		if (this.routerFunction != null) {
			ServerRequest request = ServerRequest.create(exchange, this.messageReaders);
			exchange.getAttributes().put(RouterFunctions.REQUEST_ATTRIBUTE, request);
			return this.routerFunction.route(request);
		}
		else {
			return Mono.empty();
		}
	}

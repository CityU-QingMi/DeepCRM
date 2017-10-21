	public static WebHandler toWebHandler(RouterFunction<?> routerFunction, HandlerStrategies strategies) {
		Assert.notNull(routerFunction, "RouterFunction must not be null");
		Assert.notNull(strategies, "HandlerStrategies must not be null");

		return exchange -> {
			ServerRequest request = new DefaultServerRequest(exchange, strategies.messageReaders());
			addAttributes(exchange, request);
			return routerFunction.route(request)
					.defaultIfEmpty(notFound())
					.flatMap(handlerFunction -> wrapException(() -> handlerFunction.handle(request)))
					.flatMap(response -> wrapException(() -> response.writeTo(exchange,
							new HandlerStrategiesResponseContext(strategies))));
		};
	}

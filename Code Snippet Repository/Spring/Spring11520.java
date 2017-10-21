	@Override
	protected RouterFunction<?> routerFunction() {
		NestedHandler nestedHandler = new NestedHandler();
		return nest(path("/foo/"),
				route(GET("/bar"), nestedHandler::bar)
						.andRoute(GET("/baz"), nestedHandler::baz))
				.andNest(GET("/{foo}"),
						nest(GET("/{bar}"),
								route(GET("/{baz}"), nestedHandler::variables)));
	}

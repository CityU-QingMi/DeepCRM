	@Override
	protected RouterFunction<?> routerFunction() {
		RenderingResponseHandler handler = new RenderingResponseHandler();
		RouterFunction<RenderingResponse> normalRoute = route(GET("/normal"), handler::render);
		RouterFunction<RenderingResponse> filteredRoute = route(GET("/filter"), handler::render)
				.filter(ofResponseProcessor(
						response -> RenderingResponse.from(response)
								.modelAttribute("qux", "quux")
								.build()));

		return normalRoute.and(filteredRoute);
	}

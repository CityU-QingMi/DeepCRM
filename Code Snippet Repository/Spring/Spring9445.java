	@Override
	public Mono<Void> handle(ServerHttpRequest request, ServerHttpResponse response) {
		// Remove underlying context path first (e.g. Servlet container)
		String path = request.getPath().pathWithinApplication().value();
		return this.handlerMap.entrySet().stream()
				.filter(entry -> path.startsWith(entry.getKey()))
				.findFirst()
				.map(entry -> {
					String contextPath = request.getPath().contextPath().value() + entry.getKey();
					ServerHttpRequest newRequest = request.mutate().contextPath(contextPath).build();
					return entry.getValue().handle(newRequest, response);
				})
				.orElseGet(() -> {
					response.setStatusCode(HttpStatus.NOT_FOUND);
					response.setComplete();
					return Mono.empty();
				});
	}

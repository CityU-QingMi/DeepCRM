	@Override
	public Mono<Void> handle(ServerHttpRequest request, ServerHttpResponse response) {
		ServerWebExchange exchange = createExchange(request, response);
		return getDelegate().handle(exchange)
				.onErrorResume(ex -> {
					response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
					logHandleFailure(ex);
					return Mono.empty();
				})
				.then(Mono.defer(response::setComplete));
	}

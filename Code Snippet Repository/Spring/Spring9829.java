	@Override
	public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
		if (ex instanceof ResponseStatusException) {
			exchange.getResponse().setStatusCode(((ResponseStatusException) ex).getStatus());
			if (ex.getMessage() != null) {
				logger.error(ex.getMessage());
			}
			return Mono.empty();
		}
		return Mono.error(ex);
	}

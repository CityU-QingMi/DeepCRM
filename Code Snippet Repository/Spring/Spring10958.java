		@Override
		public Mono<Void> handle(ServerWebExchange exchange) {
			if (exchange.getRequest().getQueryParams().containsKey("expire")) {
				return exchange.getSession().doOnNext(session -> {
					// Don't do anything, leave it expired...
				}).then();
			}
			else if (exchange.getRequest().getQueryParams().containsKey("changeId")) {
				return exchange.getSession().flatMap(session ->
						session.changeSessionId().doOnSuccess(aVoid -> updateSessionAttribute(session)));
			}
			else if (exchange.getRequest().getQueryParams().containsKey("invalidate")) {
				return exchange.getSession().doOnNext(WebSession::invalidate).then();
			}
			else {
				return exchange.getSession().doOnSuccess(this::updateSessionAttribute).then();
			}
		}

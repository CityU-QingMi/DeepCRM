	@Override
	public Mono<HandlerMethod> getHandlerInternal(ServerWebExchange exchange) {
		if (logger.isDebugEnabled()) {
			logger.debug("Looking up handler method for path " +
					exchange.getRequest().getPath().value());
		}
		this.mappingRegistry.acquireReadLock();
		try {
			HandlerMethod handlerMethod;
			try {
				handlerMethod = lookupHandlerMethod(exchange);
			}
			catch (Exception ex) {
				return Mono.error(ex);
			}
			if (logger.isDebugEnabled()) {
				if (handlerMethod != null) {
					logger.debug("Returning handler method [" + handlerMethod + "]");
				}
				else {
					logger.debug("Did not find handler method for " +
							"[" + exchange.getRequest().getPath().value() + "]");
				}
			}
			if (handlerMethod != null) {
				handlerMethod = handlerMethod.createWithResolvedBean();
			}
			return Mono.justOrEmpty(handlerMethod);
		}
		finally {
			this.mappingRegistry.releaseReadLock();
		}
	}

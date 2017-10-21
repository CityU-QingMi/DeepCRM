	public SessionAttributesHandler getSessionAttributesHandler(HandlerMethod handlerMethod) {
		Class<?> handlerType = handlerMethod.getBeanType();
		SessionAttributesHandler result = this.sessionAttributesHandlerCache.get(handlerType);
		if (result == null) {
			synchronized (this.sessionAttributesHandlerCache) {
				result = this.sessionAttributesHandlerCache.get(handlerType);
				if (result == null) {
					result = new SessionAttributesHandler(handlerType);
					this.sessionAttributesHandlerCache.put(handlerType, result);
				}
			}
		}
		return result;
	}

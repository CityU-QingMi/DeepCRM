	@Nullable
	public MatchableHandlerMapping getMatchableHandlerMapping(HttpServletRequest request) throws Exception {
		Assert.notNull(this.handlerMappings, "Handler mappings not initialized");
		HttpServletRequest wrapper = new RequestAttributeChangeIgnoringWrapper(request);
		for (HandlerMapping handlerMapping : this.handlerMappings) {
			Object handler = handlerMapping.getHandler(wrapper);
			if (handler == null) {
				continue;
			}
			if (handlerMapping instanceof MatchableHandlerMapping) {
				return ((MatchableHandlerMapping) handlerMapping);
			}
			throw new IllegalStateException("HandlerMapping is not a MatchableHandlerMapping");
		}
		return null;
	}

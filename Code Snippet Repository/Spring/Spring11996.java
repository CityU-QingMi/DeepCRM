	@Nullable
	protected SimpleUrlHandlerMapping buildHandlerMapping() {
		if (this.handler == null) {
			return null;
		}

		SimpleUrlHandlerMapping handlerMapping = new SimpleUrlHandlerMapping();
		handlerMapping.setUrlMap(Collections.singletonMap("/**", this.handler));
		handlerMapping.setOrder(Integer.MAX_VALUE);
		return handlerMapping;
	}

	protected void registerHandler(String urlPath, Object handler)
			throws BeansException, IllegalStateException {

		Assert.notNull(urlPath, "URL path must not be null");
		Assert.notNull(handler, "Handler object must not be null");
		Object resolvedHandler = handler;

		// Parse path pattern
		urlPath = prependLeadingSlash(urlPath);
		PathPattern pattern = getPathPatternParser().parse(urlPath);
		if (this.handlerMap.containsKey(pattern)) {
			Object existingHandler = this.handlerMap.get(pattern);
			if (existingHandler != null) {
				if (existingHandler != resolvedHandler) {
					throw new IllegalStateException(
							"Cannot map " + getHandlerDescription(handler) + " to [" + urlPath + "]: " +
							"there is already " + getHandlerDescription(existingHandler) + " mapped.");
				}
			}
		}

		// Eagerly resolve handler if referencing singleton via name.
		if (!this.lazyInitHandlers && handler instanceof String) {
			String handlerName = (String) handler;
			if (obtainApplicationContext().isSingleton(handlerName)) {
				resolvedHandler = obtainApplicationContext().getBean(handlerName);
			}
		}

		// Register resolved handler
		this.handlerMap.put(pattern, resolvedHandler);
		if (logger.isInfoEnabled()) {
			logger.info("Mapped URL path [" + urlPath + "] onto " + getHandlerDescription(handler));
		}
	}

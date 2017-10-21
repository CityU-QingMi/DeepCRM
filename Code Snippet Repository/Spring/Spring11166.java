	protected void initRouterFunctions() {
		if (logger.isDebugEnabled()) {
			logger.debug("Looking for router functions in application context: " +
					getApplicationContext());
		}

		List<RouterFunction<?>> routerFunctions = routerFunctions();
		if (!CollectionUtils.isEmpty(routerFunctions) && logger.isInfoEnabled()) {
			routerFunctions.forEach(routerFunction -> {
				logger.info("Mapped " + routerFunction);
			});
		}
		this.routerFunction = routerFunctions.stream()
				.reduce(RouterFunction::andOther)
				.orElse(null);
	}

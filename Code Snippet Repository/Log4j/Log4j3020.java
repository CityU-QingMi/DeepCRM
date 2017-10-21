    @Override
	public void contextDestroyed(final ServletContextEvent event) {
		if (this.servletContext == null || this.initializer == null) {
			LOGGER.warn("Context destroyed before it was initialized.");
			return;
		}
		LOGGER.debug("Log4jServletContextListener ensuring that Log4j shuts down properly.");

		this.initializer.clearLoggerContext(); // the application is finished
		// shutting down now
		if (initializer instanceof LifeCycle2) {
			final String stopTimeoutStr = servletContext.getInitParameter(KEY_STOP_TIMEOUT);
			final long stopTimeout = Strings.isEmpty(stopTimeoutStr) ? DEFAULT_STOP_TIMEOUT
					: Long.parseLong(stopTimeoutStr);
			final String timeoutTimeUnitStr = servletContext.getInitParameter(KEY_STOP_TIMEOUT_TIMEUNIT);
			final TimeUnit timeoutTimeUnit = Strings.isEmpty(timeoutTimeUnitStr) ? DEFAULT_STOP_TIMEOUT_TIMEUNIT
					: TimeUnit.valueOf(timeoutTimeUnitStr.toUpperCase(Locale.ROOT));
			((LifeCycle2) this.initializer).stop(stopTimeout, timeoutTimeUnit);
		} else {
			this.initializer.stop();
		}
	}

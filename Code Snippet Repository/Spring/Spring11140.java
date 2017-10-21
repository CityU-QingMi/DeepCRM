		public DefaultHandlerStrategies(
				List<HttpMessageReader<?>> messageReaders,
				List<HttpMessageWriter<?>> messageWriters,
				List<ViewResolver> viewResolvers,
				List<WebFilter> webFilters,
				List<WebExceptionHandler> exceptionHandlers,
				LocaleContextResolver localeContextResolver) {

			this.messageReaders = unmodifiableCopy(messageReaders);
			this.messageWriters = unmodifiableCopy(messageWriters);
			this.viewResolvers = unmodifiableCopy(viewResolvers);
			this.webFilters = unmodifiableCopy(webFilters);
			this.exceptionHandlers = unmodifiableCopy(exceptionHandlers);
			this.localeContextResolver = localeContextResolver;
		}

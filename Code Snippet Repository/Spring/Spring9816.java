	public DefaultServerWebExchange(ServerHttpRequest request, ServerHttpResponse response,
			WebSessionManager sessionManager, ServerCodecConfigurer codecConfigurer,
			LocaleContextResolver localeContextResolver) {

		Assert.notNull(request, "'request' is required");
		Assert.notNull(response, "'response' is required");
		Assert.notNull(sessionManager, "'sessionManager' is required");
		Assert.notNull(codecConfigurer, "'codecConfigurer' is required");
		Assert.notNull(localeContextResolver, "'localeContextResolver' is required");

		this.request = request;
		this.response = response;
		this.sessionMono = sessionManager.getSession(this).cache();
		this.localeContextResolver = localeContextResolver;
		this.formDataMono = initFormData(request, codecConfigurer);
		this.multipartDataMono = initMultipartData(request, codecConfigurer);
	}

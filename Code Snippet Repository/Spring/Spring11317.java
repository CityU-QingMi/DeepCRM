	public RequestContext(ServerWebExchange exchange, Map<String, Object> model, MessageSource messageSource,
			@Nullable RequestDataValueProcessor dataValueProcessor) {

		Assert.notNull(exchange, "ServerWebExchange is required");
		Assert.notNull(model, "Model is required");
		Assert.notNull(messageSource, "MessageSource is required");
		this.exchange = exchange;
		this.model = model;
		this.messageSource = messageSource;

		LocaleContext localeContext = exchange.getLocaleContext();
		Locale locale = localeContext.getLocale();
		this.locale = (locale != null ? locale : Locale.getDefault());
		TimeZone timeZone = (localeContext instanceof TimeZoneAwareLocaleContext ?
				((TimeZoneAwareLocaleContext) localeContext).getTimeZone() : null);
		this.timeZone = (timeZone != null ? timeZone : TimeZone.getDefault());

		this.defaultHtmlEscape = null;  // TODO
		this.dataValueProcessor = dataValueProcessor;
	}

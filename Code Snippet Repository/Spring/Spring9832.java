	@Override
	public LocaleContext resolveLocaleContext(ServerWebExchange exchange) {
		return new TimeZoneAwareLocaleContext() {
			@Override
			public Locale getLocale() {
				return locale;
			}
			@Override
			@Nullable
			public TimeZone getTimeZone() {
				return timeZone;
			}
		};
	}

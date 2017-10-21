	@Override
	public LocaleContext resolveLocaleContext(ServerWebExchange exchange) {
		ServerHttpRequest request = exchange.getRequest();
		List<Locale> acceptableLocales = request.getHeaders().getAcceptLanguageAsLocales();
		if (this.defaultLocale != null && acceptableLocales.isEmpty()) {
			return new SimpleLocaleContext(this.defaultLocale);
		}
		Locale requestLocale = acceptableLocales.isEmpty() ? null : acceptableLocales.get(0);
		if (isSupportedLocale(requestLocale)) {
			return new SimpleLocaleContext(requestLocale);
		}
		Locale supportedLocale = findSupportedLocale(request);
		if (supportedLocale != null) {
			return new SimpleLocaleContext(supportedLocale);
		}
		return (this.defaultLocale != null ? new SimpleLocaleContext(this.defaultLocale) :
				new SimpleLocaleContext(requestLocale));
	}

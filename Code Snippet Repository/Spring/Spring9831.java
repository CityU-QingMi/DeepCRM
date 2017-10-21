	@Nullable
	private Locale findSupportedLocale(ServerHttpRequest request) {
		List<Locale> requestLocales = request.getHeaders().getAcceptLanguageAsLocales();
		for (Locale locale : requestLocales) {
			if (getSupportedLocales().contains(locale)) {
				return locale;
			}
		}
		return null;
	}

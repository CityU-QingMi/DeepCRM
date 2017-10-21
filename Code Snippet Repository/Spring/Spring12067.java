	@Nullable
	private Locale findSupportedLocale(HttpServletRequest request) {
		Enumeration<Locale> requestLocales = request.getLocales();
		while (requestLocales.hasMoreElements()) {
			Locale locale = requestLocales.nextElement();
			if (getSupportedLocales().contains(locale)) {
				return locale;
			}
		}
		return null;
	}

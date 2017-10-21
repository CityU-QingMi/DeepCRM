	public static void exposeLocalizationContext(HttpServletRequest request, @Nullable MessageSource messageSource) {
		Locale jstlLocale = RequestContextUtils.getLocale(request);
		Config.set(request, Config.FMT_LOCALE, jstlLocale);
		TimeZone timeZone = RequestContextUtils.getTimeZone(request);
		if (timeZone != null) {
			Config.set(request, Config.FMT_TIME_ZONE, timeZone);
		}
		if (messageSource != null) {
			LocalizationContext jstlContext = new SpringLocalizationContext(messageSource, request);
			Config.set(request, Config.FMT_LOCALIZATION_CONTEXT, jstlContext);
		}
	}

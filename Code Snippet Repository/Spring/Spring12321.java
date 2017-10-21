	public static void exposeLocalizationContext(RequestContext requestContext) {
		Config.set(requestContext.getRequest(), Config.FMT_LOCALE, requestContext.getLocale());
		TimeZone timeZone = requestContext.getTimeZone();
		if (timeZone != null) {
			Config.set(requestContext.getRequest(), Config.FMT_TIME_ZONE, timeZone);
		}
		MessageSource messageSource = getJstlAwareMessageSource(
				requestContext.getServletContext(), requestContext.getMessageSource());
		LocalizationContext jstlContext = new SpringLocalizationContext(messageSource, requestContext.getRequest());
		Config.set(requestContext.getRequest(), Config.FMT_LOCALIZATION_CONTEXT, jstlContext);
	}

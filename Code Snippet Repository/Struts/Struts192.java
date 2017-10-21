    protected Locale getLocale(Map<String, Object> context) {
        Locale locale = null;
        if (context != null) {
            locale = (Locale) context.get(ActionContext.LOCALE);
        }
        if (locale == null) {
            LocaleProviderFactory localeProviderFactory = container.getInstance(LocaleProviderFactory.class);
            locale = localeProviderFactory.createLocaleProvider().getLocale();
        }
        return locale;
    }

    protected Locale getLocaleFromParam(Object requestedLocale) {
        LocaleProvider localeProvider = localeProviderFactory.createLocaleProvider();

        Locale locale = null;
        if (requestedLocale != null) {
            if (requestedLocale instanceof Locale) {
                locale = (Locale) requestedLocale;
            } else {
                String localeStr = requestedLocale.toString();
                if (localeProvider.isValidLocaleString(localeStr)) {
                    locale = LocaleUtils.toLocale(localeStr);
                } else {
                    locale = localeProvider.getLocale();
                }
            }
            if (locale != null) {
                LOG.debug("Found locale: {}", locale);
            }
        }

        if (locale != null && !localeProvider.isValidLocale(locale)) {
            Locale defaultLocale = localeProvider.getLocale();
            LOG.debug("Provided locale {} isn't valid, fallback to default locale", locale, defaultLocale);
            locale = defaultLocale;
        }

        return locale;
    }

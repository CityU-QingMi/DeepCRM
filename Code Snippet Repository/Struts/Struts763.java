    private LocaleProvider getLocaleProvider(Object action) {
        LocaleProvider localeProvider;
        if (action instanceof LocaleProvider) {
            localeProvider = (LocaleProvider) action;
        } else {
            LocaleProviderFactory localeProviderFactory = container.getInstance(LocaleProviderFactory.class);
            localeProvider = localeProviderFactory.createLocaleProvider();
        }
        return localeProvider;
    }

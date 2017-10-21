    private RefCapablePropertyResourceBundle(String baseName,
            PropertyResourceBundle wrappedBundle, ClassLoader loader) {
        this.baseName = baseName;
        this.wrappedBundle = wrappedBundle;
        Locale locale = wrappedBundle.getLocale();
        this.loader = loader;
        language = locale.getLanguage();
        country = locale.getCountry();
        variant = locale.getVariant();
        if (language.length() < 1) language = null;
        if (country.length() < 1) country = null;
        if (variant.length() < 1) variant = null;
    }

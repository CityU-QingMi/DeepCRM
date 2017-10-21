    public boolean start(Writer writer) {
        boolean result = super.start(writer);

        try {
            String name = this.findString(this.name, "name", "Resource bundle name is required. Example: foo or foo_en");
            ResourceBundle bundle = defaultTextProvider.getTexts(name);

            if (bundle == null) {
                LocaleProvider localeProvider = localeProviderFactory.createLocaleProvider();
                bundle = localizedTextProvider.findResourceBundle(name, localeProvider.getLocale());
            }

            if (bundle != null) {
                textProvider = textProviderFactory.createInstance(bundle);
                getStack().push(textProvider);
                pushed = true;
            }
        } catch (Exception e) {
            throw new StrutsException("Could not find the bundle " + name, e);
        }

        return result;
    }

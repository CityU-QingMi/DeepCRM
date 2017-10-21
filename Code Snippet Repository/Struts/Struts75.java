    public ResourceBundle getTexts(String bundleName) {
        // if there's one text provider that gives us a non-null resource bundle for this bundleName, we are ok, else try the next
        // text provider
        for (TextProvider textProvider : textProviders) {
            ResourceBundle bundle = textProvider.getTexts(bundleName);
            if (bundle != null) {
                return bundle;
            }
        }
        return null;
    }

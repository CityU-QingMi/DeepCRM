    public ResourceBundle getTexts() {
        // if there's one text provider that gives us a non-null resource bundle, we are ok, else try the next
        // text provider
        for (TextProvider textProvider : textProviders) {
            ResourceBundle bundle = textProvider.getTexts();
            if (bundle != null) {
                return bundle;
            }
        }
        return null;
    }

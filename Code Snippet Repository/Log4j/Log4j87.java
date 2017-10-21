    protected ResourceBundle getResourceBundle(final String rbBaseName, final Locale resourceBundleLocale,
            final boolean loop) {
        ResourceBundle rb = null;

        if (rbBaseName == null) {
            return null;
        }
        try {
            if (resourceBundleLocale != null) {
                rb = ResourceBundle.getBundle(rbBaseName, resourceBundleLocale);
            } else {
                rb = ResourceBundle.getBundle(rbBaseName);
            }
        } catch (final MissingResourceException ex) {
            if (!loop) {
                logger.debug("Unable to locate ResourceBundle " + rbBaseName);
                return null;
            }
        }

        String substr = rbBaseName;
        int i;
        while (rb == null && (i = substr.lastIndexOf('.')) > 0) {
            substr = substr.substring(0, i);
            try {
                if (resourceBundleLocale != null) {
                    rb = ResourceBundle.getBundle(substr, resourceBundleLocale);
                } else {
                    rb = ResourceBundle.getBundle(substr);
                }
            } catch (final MissingResourceException ex) {
                logger.debug("Unable to locate ResourceBundle " + substr);
            }
        }
        return rb;
    }

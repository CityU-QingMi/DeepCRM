    public static ResourceBundle getBundle(String name, Locale locale,
                                           ClassLoader cl)
                                           throws NullPointerException,
                                               MissingResourceException {

        if (cl == null) {
            return ResourceBundle.getBundle(name, locale);
        } else if (newGetBundleMethod == null) {
            return ResourceBundle.getBundle(name, locale);
        } else {
            try {
                return (ResourceBundle) newGetBundleMethod.invoke(null,
                        new Object[] {
                    name, locale, cl
                });
            } catch (Exception e) {
                return ResourceBundle.getBundle(name, locale);
            }
        }
    }

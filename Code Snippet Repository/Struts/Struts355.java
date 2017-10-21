    @Override
    public String findDefaultText(String aTextName, Locale locale) {
        List<String> localList = getCurrentBundleNames();

        for (String bundleName : localList) {
            ResourceBundle bundle = findResourceBundle(bundleName, locale);
            if (bundle != null) {
                reloadBundles();
                try {
                    return bundle.getString(aTextName);
                } catch (MissingResourceException e) {
                    // will be logged when not found in any bundle
                }
            }
        }

        if (devMode) {
            LOG.warn("Missing key [{}] in bundles [{}]!", aTextName, localList);
        } else {
            LOG.debug("Missing key [{}] in bundles [{}]!", aTextName, localList);
        }

        return null;
    }

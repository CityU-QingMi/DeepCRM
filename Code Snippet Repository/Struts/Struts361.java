    @Override
    public ResourceBundle findResourceBundle(String aBundleName, Locale locale) {
        ClassLoader classLoader = getCurrentThreadContextClassLoader();
        String key = createMissesKey(String.valueOf(classLoader.hashCode()), aBundleName, locale);

        if (missingBundles.contains(key)) {
            return null;
        }

        ResourceBundle bundle = null;
        try {
            if (bundlesMap.containsKey(key)) {
                bundle = bundlesMap.get(key);
            } else {
                bundle = ResourceBundle.getBundle(aBundleName, locale, classLoader);
                bundlesMap.putIfAbsent(key, bundle);
            }
        } catch (MissingResourceException ex) {
            if (delegatedClassLoaderMap.containsKey(classLoader.hashCode())) {
                try {
                    if (bundlesMap.containsKey(key)) {
                        bundle = bundlesMap.get(key);
                    } else {
                        bundle = ResourceBundle.getBundle(aBundleName, locale, delegatedClassLoaderMap.get(classLoader.hashCode()));
                        bundlesMap.putIfAbsent(key, bundle);
                    }
                } catch (MissingResourceException e) {
                    LOG.debug("Missing resource bundle [{}]!", aBundleName, e);
                    missingBundles.add(key);
                }
            } else {
                LOG.debug("Missing resource bundle [{}]!", aBundleName);
                missingBundles.add(key);
            }
        }
        return bundle;
    }

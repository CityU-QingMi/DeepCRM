	@Nullable
	protected ResourceBundle getResourceBundle(String basename, Locale locale) {
		if (getCacheMillis() >= 0) {
			// Fresh ResourceBundle.getBundle call in order to let ResourceBundle
			// do its native caching, at the expense of more extensive lookup steps.
			return doGetBundle(basename, locale);
		}
		else {
			// Cache forever: prefer locale cache over repeated getBundle calls.
			synchronized (this.cachedResourceBundles) {
				Map<Locale, ResourceBundle> localeMap = this.cachedResourceBundles.get(basename);
				if (localeMap != null) {
					ResourceBundle bundle = localeMap.get(locale);
					if (bundle != null) {
						return bundle;
					}
				}
				try {
					ResourceBundle bundle = doGetBundle(basename, locale);
					if (localeMap == null) {
						localeMap = new HashMap<>();
						this.cachedResourceBundles.put(basename, localeMap);
					}
					localeMap.put(locale, bundle);
					return bundle;
				}
				catch (MissingResourceException ex) {
					if (logger.isWarnEnabled()) {
						logger.warn("ResourceBundle [" + basename + "] not found for MessageSource: " + ex.getMessage());
					}
					// Assume bundle not found
					// -> do NOT throw the exception to allow for checking parent message source.
					return null;
				}
			}
		}
	}

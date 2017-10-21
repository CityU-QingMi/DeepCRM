    protected String getMessage(String bundleName, Locale locale, String key, ValueStack valueStack, Object[] args) {
        ResourceBundle bundle = findResourceBundle(bundleName, locale);
        if (bundle == null) {
            return null;
        }
        if (valueStack != null)
            reloadBundles(valueStack.getContext());
        try {
        	String message = bundle.getString(key);
        	if (valueStack != null)
        		message = TextParseUtil.translateVariables(bundle.getString(key), valueStack);
            MessageFormat mf = buildMessageFormat(message, locale);
            return formatWithNullDetection(mf, args);
        } catch (MissingResourceException e) {
            if (devMode) {
                LOG.warn("Missing key [{}] in bundle [{}]!", key, bundleName);
            } else {
                LOG.debug("Missing key [{}] in bundle [{}]!", key, bundleName);
            }
            return null;
        }
    }

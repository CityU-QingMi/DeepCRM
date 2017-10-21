    @Override
    public String findText(ResourceBundle bundle, String aTextName, Locale locale, String defaultMessage, Object[] args,
                           ValueStack valueStack) {
        try {
            reloadBundles(valueStack.getContext());

            String message = TextParseUtil.translateVariables(bundle.getString(aTextName), valueStack);
            MessageFormat mf = buildMessageFormat(message, locale);

            return formatWithNullDetection(mf, args);
        } catch (MissingResourceException ex) {
            if (devMode) {
                LOG.warn("Missing key [{}] in bundle [{}]!", aTextName, bundle);
            } else {
                LOG.debug("Missing key [{}] in bundle [{}]!", aTextName, bundle);
            }
        }

        GetDefaultMessageReturnArg result = getDefaultMessage(aTextName, locale, valueStack, args, defaultMessage);
        if (unableToFindTextForKey(result)) {
            LOG.warn("Unable to find text for key '{}' in ResourceBundles for locale '{}'", aTextName, locale);
        }
        return result != null ? result.message : null;
    }

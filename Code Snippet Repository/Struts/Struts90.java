    @Override
    public boolean isValidLocaleString(String localeStr) {
        Locale locale = null;
        try {
            locale = LocaleUtils.toLocale(StringUtils.trimToNull(localeStr));
        } catch (IllegalArgumentException e) {
            LOG.warn(new ParameterizedMessage("Cannot convert [{}] to proper locale", localeStr), e);
        }
        return isValidLocale(locale);
    }

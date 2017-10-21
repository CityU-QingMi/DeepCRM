    protected Locale getLocale(HttpServletRequest request) {
        Locale locale;
        if (defaultLocale != null) {
            try {
                locale = LocaleUtils.toLocale(defaultLocale);
            } catch (IllegalArgumentException e) {
                LOG.warn(new ParameterizedMessage("Cannot convert 'struts.locale' = [{}] to proper locale, defaulting to request locale [{}]",
                                defaultLocale, request.getLocale()), e);
                locale = request.getLocale();
            }
        } else {
            locale = request.getLocale();
        }
        return locale;
    }

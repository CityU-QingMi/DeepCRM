    @Override
    public String intercept(ActionInvocation invocation) throws Exception {
        LOG.debug("Intercept '{}/{}'", invocation.getProxy().getNamespace(), invocation.getProxy().getActionName());

        LocaleHandler localeHandler = getLocaleHandler(invocation);
        Locale locale = localeHandler.find();

        if (locale == null) {
            locale = localeHandler.read(invocation);
        }

        if (localeHandler.shouldStore()) {
            locale = localeHandler.store(invocation, locale);
        }

        useLocale(invocation, locale);

        if (LOG.isDebugEnabled()) {
            LOG.debug("Before action invocation Locale={}", invocation.getStack().findValue("locale"));
        }

        try {
            return invocation.invoke();
        } finally {
            if (LOG.isDebugEnabled()) {
                LOG.debug("After action invocation Locale={}", invocation.getStack().findValue("locale"));
            }
        }
    }

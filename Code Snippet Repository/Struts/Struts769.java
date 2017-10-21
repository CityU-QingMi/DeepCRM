    protected LocaleHandler getLocaleHandler(ActionInvocation invocation) {
        LocaleHandler localeHandler;

        if (this.storage == Storage.COOKIE) {
            localeHandler = new CookieLocaleHandler(invocation);
        } else if (this.storage == Storage.SESSION) {
            localeHandler = new SessionLocaleHandler(invocation);
        } else {
            localeHandler = new RequestOnlyLocaleHandler(invocation);
        }

        LOG.debug("Using LocaleFinder implementation {}", localeHandler.getClass().getName());
        return localeHandler;
    }

    static int getScope(final String scope) {
        if ("request".equalsIgnoreCase(scope)) {
            return PageContext.REQUEST_SCOPE;
        }
        if ("session".equalsIgnoreCase(scope)) {
            return PageContext.SESSION_SCOPE;
        }
        if ("application".equalsIgnoreCase(scope)) {
            return PageContext.APPLICATION_SCOPE;
        }
        return PageContext.PAGE_SCOPE;
    }

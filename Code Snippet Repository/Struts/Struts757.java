    protected void populateCookieValueIntoStack(String cookieName, String cookieValue, Map<String, String> cookiesMap, ValueStack stack) {
        if (cookiesValueSet.isEmpty() || cookiesValueSet.contains("*")) {
            // If the interceptor is configured to accept any cookie value
            // OR
            // no cookiesValue is defined, so as long as the cookie name match
            // we'll inject it into Struts' action
            if (LOG.isDebugEnabled()) {
                if (cookiesValueSet.isEmpty())
                    LOG.debug("no cookie value is configured, cookie with name [{}] with value [{}] will be injected", cookieName, cookieValue);
                else if (cookiesValueSet.contains("*"))
                    LOG.debug("interceptor is configured to accept any value, cookie with name [{}] with value [{}] will be injected", cookieName, cookieValue);
            }
            cookiesMap.put(cookieName, cookieValue);
            stack.setValue(cookieName, cookieValue);
        }
        else {
            // if cookiesValues is specified, the cookie's value must match before we
            // inject them into Struts' action
            if (cookiesValueSet.contains(cookieValue)) {
                LOG.debug("both configured cookie name and value matched, cookie [{}] with value [{}] will be injected", cookieName, cookieValue);

                cookiesMap.put(cookieName, cookieValue);
                stack.setValue(cookieName, cookieValue);
            }
        }
    }

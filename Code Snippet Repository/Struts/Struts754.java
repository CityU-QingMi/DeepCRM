    public String intercept(ActionInvocation invocation) throws Exception {
        LOG.debug("start interception");

        // contains selected cookies
        final Map<String, String> cookiesMap = new LinkedHashMap<>();

        Cookie[] cookies = ServletActionContext.getRequest().getCookies();
        if (cookies != null) {
            final ValueStack stack = ActionContext.getContext().getValueStack();

            for (Cookie cookie : cookies) {
                String name = cookie.getName();
                String value = cookie.getValue();

                if (isAcceptableName(name)) {
                    if (cookiesNameSet.contains("*")) {
                        LOG.debug("Contains cookie name [*] in configured cookies name set, cookie with name [{}] with value [{}] will be injected", name, value);
                        populateCookieValueIntoStack(name, value, cookiesMap, stack);
                    } else if (cookiesNameSet.contains(cookie.getName())) {
                        populateCookieValueIntoStack(name, value, cookiesMap, stack);
                    }
                } else {
                    LOG.warn("Cookie name [{}] with value [{}] was rejected!", name, value);
                }
            }
        }

        // inject the cookiesMap, even if we don't have any cookies
        injectIntoCookiesAwareAction(invocation.getAction(), cookiesMap);

        return invocation.invoke();
    }

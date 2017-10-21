        @Override
        public Locale read(ActionInvocation invocation) {
            Locale locale = null;

            Cookie[] cookies = ServletActionContext.getRequest().getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (attributeName.equals(cookie.getName())) {
                        locale = getLocaleFromParam(cookie.getValue());
                    }
                }
            }

            if (locale == null) {
                LOG.debug("No Locale defined in cookie, fetching from current request and it won't be stored!");
                shouldStore = false;
                locale = super.read(invocation);
            } else {
                LOG.debug("Found stored Locale {} in cookie, using it!", locale);
            }
            return locale;
        }

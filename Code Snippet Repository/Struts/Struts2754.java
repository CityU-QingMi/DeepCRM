        private String getScopeConstant(String scope) {
            String scopeName = "PageContext.PAGE_SCOPE"; // Default to page

            if ("request".equals(scope)) {
                scopeName = "PageContext.REQUEST_SCOPE";
            } else if ("session".equals(scope)) {
                scopeName = "PageContext.SESSION_SCOPE";
            } else if ("application".equals(scope)) {
                scopeName = "PageContext.APPLICATION_SCOPE";
            }

            return scopeName;
        }

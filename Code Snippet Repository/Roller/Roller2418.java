    public String doIntercept(ActionInvocation invocation) throws Exception {

        if (log.isDebugEnabled()) {
            log.debug("Entering UIActionInterceptor");
        }

        final Object action = invocation.getAction();
        final ActionContext context = invocation.getInvocationContext();

        HttpServletRequest request = (HttpServletRequest) context
                .get(HTTP_REQUEST);

        // is this one of our own UIAction classes?
        if (action instanceof UIAction) {

            if (log.isDebugEnabled()) {
                log.debug("action is a UIAction, setting relevant attributes");
            }

            UIAction theAction = (UIAction) action;

            // extract the authenticated user and set it
            RollerSession rses = RollerSession.getRollerSession(request);
            if (rses != null) {
                theAction.setAuthenticatedUser(rses.getAuthenticatedUser());
            }

            // extract the work weblog and set it
            String weblogHandle = theAction.getWeblog();
            if (!StringUtils.isEmpty(weblogHandle)) {
                Weblog weblog = null;
                try {
                    weblog = WebloggerFactory.getWeblogger().getWeblogManager()
                            .getWeblogByHandle(weblogHandle);
                    if (weblog != null) {
                        theAction.setActionWeblog(weblog);
                    }
                } catch (Exception e) {
                    log.error("Error looking up action weblog - "
                            + weblogHandle, e);
                }
            }
        }

        return invocation.invoke();
    }

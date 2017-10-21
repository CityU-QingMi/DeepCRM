    protected void before(ActionInvocation invocation) throws Exception {
        invocation.addPreResultListener(this);
        Map ses = ActionContext.getContext().getSession();
        if (ses == null && autoCreateSession) {
            ses = new SessionMap(ServletActionContext.getRequest());
            ActionContext.getContext().setSession(ses);
        }

        if ( ses != null) {
            lock(ses, invocation);
        }

        String key = getKey(invocation);
        Map app = ActionContext.getContext().getApplication();
        final ValueStack stack = ActionContext.getContext().getValueStack();

        LOG.debug("scope interceptor before");

        if (application != null)
            for (String string : application) {
                Object attribute = app.get(key + string);
                if (attribute != null) {
                    LOG.debug("Application scoped variable set {} = {}", string, String.valueOf(attribute));
                    stack.setValue(string, nullConvert(attribute));
                }
            }

        if (ActionContext.getContext().getParameters().get(sessionReset).isDefined()) {
            return;
        }

        if (reset) {
            return;
        }

        if (ses == null) {
            LOG.debug("No HttpSession created... Cannot set session scoped variables");
            return;
        }

        if (session != null && (!"start".equals(type))) {
            for (String string : session) {
                Object attribute = ses.get(key + string);
                if (attribute != null) {
                    LOG.debug("Session scoped variable set {} = {}", string, String.valueOf(attribute));
                    stack.setValue(string, nullConvert(attribute));
                }
            }
        }
    }

    public void beforeResult(ActionInvocation invocation, String resultCode) {
        String key = getKey(invocation);
        Map app = ActionContext.getContext().getApplication();
        final ValueStack stack = ActionContext.getContext().getValueStack();

        if (application != null)
            for (String string : application) {
                Object value = stack.findValue(string);
                LOG.debug("Application scoped variable saved {} = {}", string, String.valueOf(value));

                //if( value != null)
                app.put(key + string, nullConvert(value));
            }

        boolean ends = "end".equals(type);

        Map ses = ActionContext.getContext().getSession();
        if (ses != null) {

            if (session != null) {
                for (String string : session) {
                    if (ends) {
                        ses.remove(key + string);
                    } else {
                        Object value = stack.findValue(string);
                        LOG.debug("Session scoped variable saved {} = {}", string, String.valueOf(value));

                        // Null value should be scoped too
                        //if( value != null)
                        ses.put(key + string, nullConvert(value));
                    }
                }
            }
            unlock(ses);
        } else {
            LOG.debug("No HttpSession created... Cannot save session scoped variables.");
        }
        LOG.debug("scope interceptor after (before result)");
    }

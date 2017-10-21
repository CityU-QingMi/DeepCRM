    public String doIntercept(ActionInvocation invocation) throws Exception {

        if (log.isDebugEnabled()) {
            log.debug("Entering UIActionPrepareInterceptor");
        }

        final Object action = invocation.getAction();

        // is this one of our own UIAction classes?
        if (action instanceof UIActionPreparable) {

            if (log.isDebugEnabled()) {
                log.debug("action is UIActionPreparable, calling myPrepare() method");
            }

            UIActionPreparable theAction = (UIActionPreparable) action;
            theAction.myPrepare();
        }

        return invocation.invoke();
    }

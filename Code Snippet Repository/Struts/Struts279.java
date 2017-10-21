    @Override
    public String doIntercept(ActionInvocation invocation) throws Exception {
        Object action = invocation.getAction();

        if (action instanceof Preparable) {
            try {
                String[] prefixes;
                if (firstCallPrepareDo) {
                    prefixes = new String[] {ALT_PREPARE_PREFIX, PREPARE_PREFIX};
                } else {
                    prefixes = new String[] {PREPARE_PREFIX, ALT_PREPARE_PREFIX};
                }
                PrefixMethodInvocationUtil.invokePrefixMethod(invocation, prefixes);
            }
            catch (InvocationTargetException e) {
/**/
/**/
/**/
/**/
/**/
/**/
                Throwable cause = e.getCause();
                if (cause instanceof Exception) {
                    throw (Exception) cause;
                } else if(cause instanceof Error) {
                    throw (Error) cause;
                } else {
/**/
/**/
/**/
/**/
                    throw e;
                }
            }

            if (alwaysInvokePrepare) {
                ((Preparable) action).prepare();
            }
        }

        return invocation.invoke();
    }

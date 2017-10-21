    private void copyStack(ActionInvocation invocation, CompoundRoot root) {
        List list = prepareList(root);
        Map<String, Object> ctxMap = invocation.getInvocationContext().getContextMap();
        for (Object object : list) {
            if (shouldCopy(object)) {
                Object action = invocation.getAction();
                Class<?> editable = null;
                if(ProxyUtil.isProxy(action)) {
                    editable = ProxyUtil.ultimateTargetClass(action);
                }
                reflectionProvider.copy(object, action, ctxMap, prepareExcludes(), includes, editable);
            }
        }
    }

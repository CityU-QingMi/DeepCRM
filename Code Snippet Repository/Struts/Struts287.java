    public String intercept(ActionInvocation invocation) throws Exception {
        final Object action = invocation.getAction();
        invocation.addPreResultListener(this);
        List<Method> methods = new ArrayList<>(MethodUtils.getMethodsListWithAnnotation(action.getClass(), Before.class,
                true, true));
        if (methods.size() > 0) {
            // methods are only sorted by priority
            Collections.sort(methods, new Comparator<Method>() {
                public int compare(Method method1, Method method2) {
                    return comparePriorities(MethodUtils.getAnnotation(method1, Before.class, true,
                            true).priority(), MethodUtils.getAnnotation(method2, Before.class, true,
                            true).priority());
                }
            });
            for (Method m : methods) {
                final String resultCode = (String) MethodUtils.invokeMethod(action, true, m.getName());
                if (resultCode != null) {
                    // shortcircuit execution
                    return resultCode;
                }
            }
        }

        String invocationResult = invocation.invoke();

        // invoke any @After methods
        methods = new ArrayList<Method>(MethodUtils.getMethodsListWithAnnotation(action.getClass(), After.class,
                true, true));

        if (methods.size() > 0) {
            // methods are only sorted by priority
            Collections.sort(methods, new Comparator<Method>() {
                public int compare(Method method1, Method method2) {
                    return comparePriorities(MethodUtils.getAnnotation(method1, After.class, true,
                            true).priority(), MethodUtils.getAnnotation(method2, After.class, true,
                            true).priority());
                }
            });
            for (Method m : methods) {
                MethodUtils.invokeMethod(action, true, m.getName());
            }
        }

        return invocationResult;
    }

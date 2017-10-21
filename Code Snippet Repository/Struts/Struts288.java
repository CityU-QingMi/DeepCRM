    public void beforeResult(ActionInvocation invocation, String resultCode) {
        Object action = invocation.getAction();
        List<Method> methods = new ArrayList<Method>(MethodUtils.getMethodsListWithAnnotation(action.getClass(),
                BeforeResult.class, true, true));

        if (methods.size() > 0) {
            // methods are only sorted by priority
            Collections.sort(methods, new Comparator<Method>() {
                public int compare(Method method1, Method method2) {
                    return comparePriorities(MethodUtils.getAnnotation(method1, BeforeResult.class, true,
                            true).priority(), MethodUtils.getAnnotation(method2, BeforeResult.class,
                            true, true).priority());
                }
            });
            for (Method m : methods) {
                try {
                    MethodUtils.invokeMethod(action, true, m.getName());
                } catch (Exception e) {
                    throw new XWorkException(e);
                }
            }
        }
    }

    public static Object profiledInvoke(Method target, Object value, Object[] args) throws IllegalAccessException, InvocationTargetException {
        //if we are not active - then do nothing
        if (!UtilTimerStack.isActive()) {
            return target.invoke(value, args);
        }

        String logLine = getTrimmedClassName(target) + "." + target.getName() + "()";

        UtilTimerStack.push(logLine);
        try {
            Object returnValue = target.invoke(value, args);

            //if the return value is an interface then we should also proxy it!
            if (returnValue != null && target.getReturnType().isInterface()) {
                InvocationHandler timerHandler = new TimerInvocationHandler(returnValue);
                return Proxy.newProxyInstance(returnValue.getClass().getClassLoader(),
                        new Class[]{target.getReturnType()}, timerHandler);
            } else {
                return returnValue;
            }
        } finally {
            UtilTimerStack.pop(logLine);
        }
    }

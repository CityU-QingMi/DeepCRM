    public static Object getProfiledObject(Class interfaceClazz, Object o) {
        //if we are not active - then do nothing
        if (!UtilTimerStack.isActive()) {
            return o;
        }

        //this should always be true - you shouldn't be passing something that isn't an interface
        if (interfaceClazz.isInterface()) {
            InvocationHandler timerHandler = new TimerInvocationHandler(o);
            return Proxy.newProxyInstance(interfaceClazz.getClassLoader(),
                    new Class[]{interfaceClazz}, timerHandler);
        } else {
            return o;
        }
    }

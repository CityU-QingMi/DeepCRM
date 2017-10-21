    public static Method getMethod(Object base, Object property,
            Class[] paramTypes) throws MethodNotFoundException {
        if (base == null || property == null) {
            throw new MethodNotFoundException(MessageFactory.get(
                    "error.method.notfound", base, property,
                    paramString(paramTypes)));
        }

        String methodName = (property instanceof String) ? (String) property
                : property.toString();

        Method method = null;
        try {
            method = base.getClass().getMethod(methodName, paramTypes);
        } catch (NoSuchMethodException nsme) {
            throw new MethodNotFoundException(MessageFactory.get(
                    "error.method.notfound", base, property,
                    paramString(paramTypes)));
        }
        return method;
    }

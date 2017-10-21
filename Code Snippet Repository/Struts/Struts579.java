    @SuppressWarnings("")
    protected Class getVisitorReturnType(Class actionClass, String visitorFieldName) {
        if (visitorFieldName == null) {
            return null;
        }
        String methodName = "get" + StringUtils.capitalize(visitorFieldName);
        try {
            Method method = actionClass.getMethod(methodName, new Class[0]);
            return method.getReturnType();
        } catch (NoSuchMethodException e) {
            return null;
        }
    }

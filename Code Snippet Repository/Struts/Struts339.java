    @Override
    public Object callStaticMethod(Map context, Class aClass, String string, Object[] objects) throws MethodFailedException {
        Boolean exec = (Boolean) context.get(ReflectionContextState.DENY_METHOD_EXECUTION);
        boolean e = ((exec == null) ? false : exec.booleanValue());

        if (!e) {
            return callStaticMethodWithDebugInfo(context, aClass, string, objects);
        } else {
            return null;
        }
    }

    @SuppressWarnings("")
    protected T getGxpClosure(T body, Map params) {
        final Map overrides = getOverrides(body, params);

        Object[] args = getArgListFromValueStack(overrides).toArray();

        try {
            return (T) getGxpClosureMethod.invoke(getGxpInstance(), args);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException(createDebugString(args, e), e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(createDebugString(args, e), e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(createDebugString(args, e), e);
        }
    }

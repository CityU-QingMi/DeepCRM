    @Override
    protected void before() throws Throwable {
        threadContextHolder = new ThreadContextHolder(restoreMap, restoreStack);
        if (restoreMap) {
            ThreadContext.clearMap();
        }
        if (restoreStack) {
            ThreadContext.clearStack();
        }
    }

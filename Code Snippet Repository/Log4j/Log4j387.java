    public void restore() {
        if (restoreStack) {
            ThreadContext.setStack(immutableStack);
        }
        if (restoreContext) {
            // TODO LOG4J2-1517 Add ThreadContext.setContext(Map<String, String>)
            // Use:
            // ThreadContext.setContext(immutableContext);
            // Instead of:
            ThreadContext.clearMap();
            ThreadContext.putAll(immutableContext);
            //
            // or:
            // ThreadContext.clearMap();
            // for (Map.Entry<String, String> entry : immutableContext.entrySet()) {
            // ThreadContext.put(entry.getKey(), entry.getValue());
            // }
        }
    }

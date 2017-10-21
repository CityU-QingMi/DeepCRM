    static Map getInvocationMap() {
        Map session = ActionContext.getContext().getSession();

        if (session == null) {
            throw new IllegalStateException("Unable to access the session.");
        }

        Map invocationMap = (Map) session.get(INVOCATION_MAP_KEY);

        if (invocationMap == null) {
            invocationMap = new HashMap();
            setInvocationMap(invocationMap);
        }

        return invocationMap;
    }

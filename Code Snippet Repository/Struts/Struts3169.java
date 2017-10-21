    protected Map getOverrides(T body, Map params) {
        final Map overrides = new HashMap();
        if (hasBodyParam && body != null) {
            overrides.put(Param.BODY_PARAM_NAME, body);
        }
        if (params != null) {
            overrides.putAll(params);
        }
        return overrides;
    }

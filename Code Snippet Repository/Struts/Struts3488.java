    public Result buildResult(ResultConfig resultConfig, Map extraContext)
            throws Exception {
        if (extraContext == null) {
            extraContext = new HashMap();
        }

        extraContext.put(PLEXUS_COMPONENT_TYPE, Result.class.getName());

        return super.buildResult(resultConfig, extraContext);
    }

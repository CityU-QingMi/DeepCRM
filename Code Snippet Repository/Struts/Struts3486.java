    public Object buildAction(String actionName, String namespace, ActionConfig config, Map extraContext)
            throws Exception {
        if (extraContext == null) {
            extraContext = new HashMap();
        }

        extraContext.put(PLEXUS_COMPONENT_TYPE, Action.class.getName());

        return super.buildAction(actionName, namespace, config, extraContext);
    }

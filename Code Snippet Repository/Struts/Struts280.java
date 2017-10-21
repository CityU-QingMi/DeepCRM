    protected Object resolveModel(ObjectFactory factory, ActionContext actionContext, String modelClassName, String modelScope, String modelName) throws Exception {
        Object model;
        Map<String, Object> scopeMap = actionContext.getContextMap();
        if ("session".equals(modelScope)) {
            scopeMap = actionContext.getSession();
        }
        
        model = scopeMap.get(modelName);
        if (model == null) {
            model = factory.buildBean(modelClassName, null);
            scopeMap.put(modelName, model);
        }
        return model;
    }

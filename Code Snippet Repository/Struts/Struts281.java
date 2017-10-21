    @Override
    public String intercept(ActionInvocation invocation) throws Exception {
        Object action = invocation.getAction();

        if (action instanceof ScopedModelDriven) {
            ScopedModelDriven modelDriven = (ScopedModelDriven) action;
            if (modelDriven.getModel() == null) {
                ActionContext ctx = ActionContext.getContext();
                ActionConfig config = invocation.getProxy().getConfig();
                
                String cName = className;
                if (cName == null) {
                    try {
                        Method method = action.getClass().getMethod(GET_MODEL, EMPTY_CLASS_ARRAY);
                        Class cls = method.getReturnType();
                        cName = cls.getName();
                    } catch (NoSuchMethodException e) {
                        throw new XWorkException("The " + GET_MODEL + "() is not defined in action " + action.getClass() + "", config);
                    }
                }
                String modelName = name;
                if (modelName == null) {
                    modelName = cName;
                }
                Object model = resolveModel(objectFactory, ctx, cName, scope, modelName);
                modelDriven.setModel(model);
                modelDriven.setScopeKey(modelName);
            }
        }
        return invocation.invoke();
    }

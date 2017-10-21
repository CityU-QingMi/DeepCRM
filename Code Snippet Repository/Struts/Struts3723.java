    @Override
    protected ViewPreparer createPreparer(String name) {
        ActionContext actionContext = ActionContext.getContext();
        if (actionContext == null) {
            LOG.warn("Action context not initialised, request has omitted an action? Fallback to super.createPreparer!");
            return super.createPreparer(name);
        }

        try {
            ObjectFactory factory = actionContext.getContainer().getInstance(ObjectFactory.class);
            return (ViewPreparer) factory.buildBean(name, ActionContext.getContext().getContextMap());
        } catch (Exception e) {
            LOG.error(new ParameterizedMessage("Cannot create a ViewPreparer [{}], fallback to super.createPreparer!", name), e);
            return super.createPreparer(name);
        }
    }

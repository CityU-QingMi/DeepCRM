    protected void populateParams() {
        super.populateParams();

        ActionComponent action = (ActionComponent) component;
        action.setName(name);
        action.setNamespace(namespace);
        action.setExecuteResult(executeResult);
        action.setIgnoreContextParams(ignoreContextParams);
        action.setFlush(flush);
        action.setRethrowException(rethrowException);
    }

    public String execute() throws Exception {
        super.execute();
        config = configHelper.getActionConfig(namespace, actionName);
        actionNames = new TreeSet<String>(configHelper.getActionNames(namespace));
        try {
            Object action = objectFactory.buildAction(actionName, namespace, config, null);
            properties = reflectionProvider.getPropertyDescriptors(action);
        } catch (Exception e) {
            LOG.error("Unable to get properties for action " + actionName, e);
            addActionError("Unable to retrieve action properties: " + e.toString());
        }

        if (hasErrors()) //super might have set some :)
            return ERROR;
        else
            return SUCCESS;
    }

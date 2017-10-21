    public String execute() throws Exception {
        namespaces = configHelper.getNamespaces();
        if (namespaces.size() == 0) {
            addActionError("There are no namespaces in this configuration");
            return ERROR;
        }
        if (namespace == null) {
            namespace = "";
        }
        actionNames = new TreeSet<String>(configHelper.getActionNames(namespace));
        return SUCCESS;
    }

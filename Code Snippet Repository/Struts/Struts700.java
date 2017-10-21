    public DefaultActionMapper() {
        prefixTrie = new PrefixTrie() {
            {
                put(METHOD_PREFIX, new ParameterAction() {
                    public void execute(String key, ActionMapping mapping) {
                        if (allowDynamicMethodCalls) {
                            mapping.setMethod(cleanupMethodName(key.substring(METHOD_PREFIX.length())));
                        }
                    }
                });

                put(ACTION_PREFIX, new ParameterAction() {
                    public void execute(final String key, ActionMapping mapping) {
                        if (allowActionPrefix) {
                            String name = key.substring(ACTION_PREFIX.length());
                            if (allowDynamicMethodCalls) {
                                int bang = name.indexOf('!');
                                if (bang != -1) {
                                    String method = cleanupMethodName(name.substring(bang + 1));
                                    mapping.setMethod(method);
                                    name = name.substring(0, bang);
                                }
                            }
                            String actionName = cleanupActionName(name);
                            if (allowSlashesInActionNames && !allowActionCrossNamespaceAccess) {
                                if (actionName.startsWith("/")) {
                                    actionName = actionName.substring(1);
                                }
                            }
                            if (!allowSlashesInActionNames && !allowActionCrossNamespaceAccess) {
                                if (actionName.lastIndexOf("/") != -1) {
                                    actionName = actionName.substring(actionName.lastIndexOf("/") + 1);
                                }
                            }
                            mapping.setName(actionName);
                        }
                    }
                });

            }
        };
    }

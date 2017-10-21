    void parseModeConfig(Map<PortletMode, ActionMapping> actionMap, PortletConfig portletConfig,
                         PortletMode portletMode, String nameSpaceParam,
                         String defaultActionParam) {
        String namespace = portletConfig.getInitParameter(nameSpaceParam);
        if (StringUtils.isEmpty(namespace)) {
            namespace = "";
        }
        modeMap.put(portletMode, namespace);
        String defaultAction = portletConfig.getInitParameter(defaultActionParam);
        String method = null;
        if (StringUtils.isEmpty(defaultAction)) {
            defaultAction = DEFAULT_ACTION_NAME;
        }
        if (defaultAction.indexOf('!') >= 0) {
            method = defaultAction.substring(defaultAction.indexOf('!') + 1);
            defaultAction = defaultAction.substring(0, defaultAction.indexOf('!'));
        }
        StringBuilder fullPath = new StringBuilder();
        if (StringUtils.isNotEmpty(portletNamespace)) {
            fullPath.append(portletNamespace);
        }
        if (StringUtils.isNotEmpty(namespace)) {
            fullPath.append(namespace).append("/");
        } else {
            fullPath.append("/");
        }
        fullPath.append(defaultAction);
        ActionMapping mapping = new ActionMapping();
        mapping.setName(getActionName(fullPath.toString()));
        mapping.setNamespace(getNamespace(fullPath.toString()));
        if (method != null) {
            mapping.setMethod(method);
        }
        actionMap.put(portletMode, mapping);
    }

    protected ActionMapping getActionMapping(final PortletRequest portletRequest, final HttpServletRequest servletRequest) {
        ActionMapping mapping;
        String actionPath = getDefaultActionPath(portletRequest);
        if (resetAction(portletRequest)) {
            mapping = actionMap.get(portletRequest.getPortletMode());
        } else {
            actionPath = servletRequest.getParameter(ACTION_PARAM);
            if (StringUtils.isEmpty(actionPath)) {
                mapping = actionMap.get(portletRequest.getPortletMode());
            } else {

                // Use the usual action mapper, but it is expecting an action extension
                // on the uri, so we add the default one, which should be ok as the
                // portlet is a portlet first, a servlet second
                mapping = actionMapper.getMapping(servletRequest, dispatcherUtils.getConfigurationManager());
            }
        }

        if (mapping == null) {
            throw new StrutsException("Unable to locate action mapping for request, probably due to an invalid action path: " + actionPath);
        }
        return mapping;
    }

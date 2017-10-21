    private PortletMode getPortletMode(PortletRequest portletReq,
            String portletMode) {
        PortletMode mode = portletReq.getPortletMode();

        if (StringUtils.isNotEmpty(portletMode)) {
            if (PORTLETMODE_NAME_EDIT.equalsIgnoreCase(portletMode)) {
                mode = PortletMode.EDIT;
            } else if (PORTLETMODE_NAME_VIEW.equalsIgnoreCase(portletMode)) {
                mode = PortletMode.VIEW;
            } else if (PORTLETMODE_NAME_HELP.equalsIgnoreCase(portletMode)) {
                mode = PortletMode.HELP;
            }
        }
        if(mode == null) {
            mode = PortletMode.VIEW;
        }
        return mode;
    }

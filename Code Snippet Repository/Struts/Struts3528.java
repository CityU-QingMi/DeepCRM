    private WindowState getWindowState(PortletRequest portletReq,
            String windowState) {
        WindowState state = portletReq.getWindowState();
        if (StringUtils.isNotEmpty(windowState)) {
            if ("maximized".equalsIgnoreCase(windowState)) {
                state = WindowState.MAXIMIZED;
            } else if ("normal".equalsIgnoreCase(windowState)) {
                state = WindowState.NORMAL;
            } else if ("minimized".equalsIgnoreCase(windowState)) {
                state = WindowState.MINIMIZED;
            }
        }
        if(state == null) {
            state = WindowState.NORMAL;
        }
        return state;
    }

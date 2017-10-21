    private boolean resetAction(PortletRequest request) {
        boolean reset = false;
        Map paramMap = request.getParameterMap();
        String[] modeParam = (String[]) paramMap.get(MODE_PARAM);
        if (modeParam != null && modeParam.length == 1) {
            String originatingMode = modeParam[0];
            String currentMode = request.getPortletMode().toString();
            if (!currentMode.equals(originatingMode)) {
                reset = true;
            }
        }
        if (reset) {
            request.setAttribute(ACTION_RESET, Boolean.TRUE);
        } else {
            request.setAttribute(ACTION_RESET, Boolean.FALSE);
        }
        return reset;
    }

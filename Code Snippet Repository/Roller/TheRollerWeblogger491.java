    public static DeviceType getRequestType(HttpServletRequest request) {
        DeviceType type = DeviceType.standard;

        String deviceTypeParam = request.getParameter(USER_AGENT_PARAMETER);
        if (deviceTypeParam != null) {
            return deviceTypeParam.trim().equals("standard") ? DeviceType.standard
                    : DeviceType.mobile;
        }

        String cookie = getCookieValue(request.getCookies(), USER_REQUEST_TYPE,
                null);
        if (cookie != null) {
            return cookie.equals("standard") ? DeviceType.standard
                    : DeviceType.mobile;
        }

        if (isMobileDevice(request)) {
            type = DeviceType.mobile;
        }
        return type;
    }

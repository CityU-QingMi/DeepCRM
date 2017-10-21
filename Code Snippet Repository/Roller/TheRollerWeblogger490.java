    public static boolean isMobileDevice(HttpServletRequest request) {
        String userAgent = request.getHeader("User-Agent");
        if (userAgent != null) {
            userAgent = request.getHeader("User-Agent").toLowerCase();

            try {
                return (userAgent.matches(POSSIBLE_DEVICES_1) || userAgent
                        .substring(0, 4).matches(POSSIBLE_DEVICES_2));
            } catch (StringIndexOutOfBoundsException e) {
                // invalid device
                log.error("ERROR invalid userAgent type : " + userAgent);
                return false;
            }

        }
        return false;
    }

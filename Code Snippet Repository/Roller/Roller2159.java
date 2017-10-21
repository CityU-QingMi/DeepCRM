    public boolean isUserAuthorizedToAdmin(WeblogWrapper weblog) {
        try {
            if (parsedRequest.getAuthenticUser() != null) {
                return weblog.getPojo().hasUserPermission(
                        parsedRequest.getUser(), WeblogPermission.ADMIN);
            }
        } catch (Exception e) {
            log.warn("ERROR: checking user authorization", e);
        }
        return false;
    }

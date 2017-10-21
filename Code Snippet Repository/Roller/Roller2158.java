    public boolean isUserAuthorizedToAuthor(WeblogWrapper weblog) {
        try {
            if (parsedRequest.getAuthenticUser() != null) {
                return weblog.getPojo().hasUserPermission(
                        parsedRequest.getUser(), WeblogPermission.POST);
            }
        } catch (Exception e) {
            log.warn("ERROR: checking user authorization", e);
        }
        return false;
    }

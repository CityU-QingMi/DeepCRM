    public UserWrapper getUser() {
        try {
            RollerSession rses = RollerSession.getRollerSession(request);
            if (rses != null && rses.getAuthenticatedUser() != null) {
                return UserWrapper.wrap(rses.getAuthenticatedUser());
            }
        } catch (Exception e) {
            log.warn("ERROR: checking user authorization", e);
        }
        return null;
    }

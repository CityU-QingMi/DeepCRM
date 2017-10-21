    public User getCreator() {
        try {
            return WebloggerFactory.getWeblogger().getUserManager()
                    .getUserByUserName(getCreatorUserName());
        } catch (Exception e) {
            log.error("ERROR fetching user object for username: "
                    + getCreatorUserName(), e);
        }
        return null;
    }

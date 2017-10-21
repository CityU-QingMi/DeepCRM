    @SkipValidation
    @Override
    public String execute() {
        try {
            User ud = getAuthenticatedUser();
        } catch (Exception ex) {
            log.error("ERROR fetching user information", ex);
        }
        return SUCCESS;
    }

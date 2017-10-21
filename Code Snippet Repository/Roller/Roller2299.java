    @SkipValidation
    public String execute() {

        // check if blog administrator has enabled creation of new blogs
        if(!WebloggerRuntimeConfig.getBooleanProperty("site.allowUserWeblogCreation")) {
            addError("createWebsite.disabled");
            return DISABLED_RETURN_CODE;
        }

        User user = getAuthenticatedUser();

        try {
            if (!WebloggerConfig.getBooleanProperty("groupblogging.enabled")) {
                UserManager mgr = WebloggerFactory.getWeblogger().getUserManager();
                List<WeblogPermission> permissions = mgr.getWeblogPermissions(user);
                if (permissions.size() > 0) {
                    // sneaky user trying to get around 1 blog limit that applies
                    // only when group blogging is disabled
                    addError("createWebsite.oneBlogLimit");
                    return DISABLED_RETURN_CODE;
                }
            }
        } catch (WebloggerException ex) {
            log.error("error checking for existing weblogs count", ex);
            addError("generic.error.check.logs");
            return DISABLED_RETURN_CODE;
        }
        
        // pre-populate with some logical defaults
        getBean().setLocale(user.getLocale());
        getBean().setTimeZone(user.getTimeZone());
        getBean().setEmailAddress(user.getEmailAddress());
        
        return INPUT;
    }

    public boolean hasWritePermissions(User user) throws WebloggerException {
        
        // global admins can hack whatever they want
        GlobalPermission adminPerm = 
            new GlobalPermission(Collections.singletonList(GlobalPermission.ADMIN));
        boolean hasAdmin = WebloggerFactory.getWeblogger().getUserManager()
            .checkPermission(adminPerm, user); 
        if (hasAdmin) {
            return true;
        }
        
        WeblogPermission perm = null;
        try {
            // if user is an author then post status defaults to PUBLISHED, otherwise PENDING
            UserManager umgr = WebloggerFactory.getWeblogger().getUserManager();
            perm = umgr.getWeblogPermission(getWebsite(), user);
            
        } catch (WebloggerException ex) {
            // security interceptor should ensure this never happens
            mLogger.error("ERROR retrieving user's permission", ex);
            return false;
        }

        boolean author = perm.hasAction(WeblogPermission.POST) || perm.hasAction(WeblogPermission.ADMIN);
        boolean limited = !author && perm.hasAction(WeblogPermission.EDIT_DRAFT);
        
        return author || (limited && (status == PubStatus.DRAFT || status == PubStatus.PENDING));
    }

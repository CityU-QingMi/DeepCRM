    public static void teardownPermissions(WeblogPermission perm)
            throws Exception {

        // remove all permissions
        UserManager mgr = WebloggerFactory.getWeblogger().getUserManager();
        mgr.revokeWeblogPermission(perm.getWeblog(), perm.getUser(),
                WeblogPermission.ALL_ACTIONS);

        // flush to db
        WebloggerFactory.getWeblogger().flush();
    }

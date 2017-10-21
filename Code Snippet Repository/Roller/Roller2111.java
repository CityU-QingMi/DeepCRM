    public Menu getAdminMenu() {
        try {
            GlobalPermission adminPerm = 
                new GlobalPermission(Collections.singletonList(GlobalPermission.ADMIN));
            boolean hasAdmin = WebloggerFactory.getWeblogger().getUserManager()
                    .checkPermission(adminPerm, pageRequest.getUser());            
            if (pageRequest.isLoggedIn() && hasAdmin) {
                return MenuHelper.getMenu("admin", "noAction", pageRequest.getUser(), pageRequest.getWeblog());
            }
        } catch (WebloggerException ex) {
            logger.debug("ERROR: fetching user roles");
        }
        return null;
    }

    public void myPrepare() {
        if (isAdd()) {
            // create new User
            user = new User();
        } else {
            try {
                // load the user object we are modifying
                UserManager mgr = WebloggerFactory.getWeblogger().getUserManager();
                if (bean.getId() != null) {
                    // action came from CreateUser or return from ModifyUser
                    user = mgr.getUser(getBean().getId());
                } else if (bean.getUserName() != null) {
                    // action came from UserAdmin screen.
                    user = mgr.getUserByUserName(getBean().getUserName(), null);
                }
            } catch (Exception e) {
                log.error("Error looking up user (id/username) :" + bean.getId() + "/" + bean.getUserName(), e);
            }
        }
    }

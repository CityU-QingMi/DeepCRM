    public void addUser(User newUser) throws WebloggerException {

        if (newUser == null) {
            throw new WebloggerException("cannot add null user");
        }
        
        boolean adminUser = false;
        List existingUsers = this.getUsers(Boolean.TRUE, null, null, 0, 1);
        boolean firstUserAdmin = WebloggerConfig.getBooleanProperty("users.firstUserAdmin");
        if (existingUsers.size() == 0 && firstUserAdmin) {
            // Make first user an admin
            adminUser = true;

            //if user was disabled (because of activation user 
            // account with e-mail property), enable it for admin user
            newUser.setEnabled(Boolean.TRUE);
            newUser.setActivationCode(null);
        }

        if (getUserByUserName(newUser.getUserName()) != null ||
                getUserByUserName(newUser.getUserName().toLowerCase()) != null) {
            throw new WebloggerException("error.add.user.userNameInUse");
        }

        this.strategy.store(newUser);

        grantRole("editor", newUser);
        if (adminUser) {
            grantRole("admin", newUser);
        }
    }

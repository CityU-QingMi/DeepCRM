    public void copyFrom(User dataHolder) {

        this.id = dataHolder.getId();
        this.userName = dataHolder.getUserName();
        this.password = dataHolder.getPassword();
        this.screenName = dataHolder.getScreenName();
        this.fullName = dataHolder.getFullName();
        this.emailAddress = dataHolder.getEmailAddress();
        this.locale = dataHolder.getLocale();
        this.timeZone = dataHolder.getTimeZone();
        this.openIdUrl = dataHolder.getOpenIdUrl();
        this.enabled = dataHolder.getEnabled();
        this.activationCode = dataHolder.getActivationCode();

        try {
            GlobalPermission adminPerm = 
                new GlobalPermission(Collections.singletonList(GlobalPermission.ADMIN));
            this.administrator = WebloggerFactory.getWeblogger().getUserManager()
                    .checkPermission(adminPerm, dataHolder);

        } catch (WebloggerException ex) {}
    }

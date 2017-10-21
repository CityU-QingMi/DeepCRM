    public void copyFrom(User dataHolder) {
        // avoiding password, that is handled separately in class Profile
        this.id = dataHolder.getId();
        this.userName = dataHolder.getUserName();
        this.screenName = dataHolder.getScreenName();
        this.fullName = dataHolder.getFullName();
        this.emailAddress = dataHolder.getEmailAddress();
        this.locale = dataHolder.getLocale();
        this.timeZone = dataHolder.getTimeZone();
        this.openIdUrl = dataHolder.getOpenIdUrl();
    }

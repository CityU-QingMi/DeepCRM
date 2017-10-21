    public void copyTo(User dataHolder) {
        
        dataHolder.setScreenName(this.screenName);
        dataHolder.setFullName(this.fullName);
        dataHolder.setEmailAddress(this.emailAddress);
        dataHolder.setLocale(this.locale);
        dataHolder.setTimeZone(this.timeZone);
        dataHolder.setOpenIdUrl(this.openIdUrl);
        dataHolder.setEnabled(this.enabled);
        dataHolder.setActivationCode(this.activationCode);
    }

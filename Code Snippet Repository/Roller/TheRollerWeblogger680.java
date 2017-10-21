    public void myValidate() {
        if (StringUtils.isEmpty(getBean().getOpenIdUrl())) {
            // check that passwords match if they were specified (w/StringUtils.equals, null == null)
            if (!StringUtils.equals(getBean().getPasswordText(), getBean().getPasswordConfirm())) {
                addError("userRegister.error.mismatchedPasswords");
            }
            if (authMethod == AuthMethod.OPENID) {
                addError("userRegister.error.missingOpenID");
            }
        } else {
            // check that OpenID, if provided, is not taken
            try {
                UserManager mgr = WebloggerFactory.getWeblogger().getUserManager();
                User user = mgr.getUserByOpenIdUrl(bean.getOpenIdUrl());
                if (user != null && !(user.getUserName().equals(bean.getUserName()))) {
                    addError("error.add.user.openIdInUse");
                }
            } catch (WebloggerException ex) {
                log.error("error checking OpenID URL", ex);
                addError("generic.error.check.logs");
            }
        }
    }

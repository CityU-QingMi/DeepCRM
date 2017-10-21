    private void myValidate() {
        if (isAdd()) {
            String allowed = WebloggerConfig.getProperty("username.allowedChars");
            if(allowed == null || allowed.trim().length() == 0) {
                allowed = Register.DEFAULT_ALLOWED_CHARS;
            }
            String safe = CharSetUtils.keep(getBean().getUserName(), allowed);

            if (StringUtils.isEmpty(getBean().getUserName())) {
                addError("error.add.user.missingUserName");
            } else if (!safe.equals(getBean().getUserName()) ) {
                addError("error.add.user.badUserName");
            }
            if ((authMethod == AuthMethod.ROLLERDB ||
                    (authMethod == AuthMethod.DB_OPENID && StringUtils.isEmpty(getBean().getOpenIdUrl())))
                    && StringUtils.isEmpty(getBean().getPassword())) {
                addError("error.add.user.missingPassword");
            }
        }
        else {
            if (user.getUserName() == null) {
                addError("userAdmin.error.userNotFound");
            }
        }
        if ((authMethod == AuthMethod.OPENID) && StringUtils.isEmpty(getBean().getOpenIdUrl())) {
            addError("userRegister.error.missingOpenID");
        }

        // check that OpenID, if provided, is not taken
        if (!StringUtils.isEmpty(getBean().getOpenIdUrl())) {
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
